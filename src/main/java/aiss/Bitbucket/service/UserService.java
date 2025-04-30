package aiss.Bitbucket.service;

import aiss.Bitbucket.model.User;
import aiss.Bitbucket.model.Dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.Base64;


@Service
public class UserService {

    @Value("${token}")
    private String token;

    @Autowired
    private RestTemplate restTemplate;

    private final String username = null; // Tu usuario de Bitbucket
    private final String baseUri = "https://api.bitbucket.org/2.0/user";

    public User getUser() {

        // CONSTRUIR LA CABEZERA
        String auth = username + ":" + token;
        byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes(StandardCharsets.US_ASCII));
        String authHeader = "Basic " + new String(encodedAuth);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authHeader);
        headers.set("Accept", "application/json");
        HttpEntity<String> request = new HttpEntity<>(null, headers);

        ResponseEntity<UserDto> response = restTemplate.exchange(
                baseUri, HttpMethod.GET, request, UserDto.class
        );

        UserDto dto = response.getBody();

        if (dto == null) {
            throw new RuntimeException("No se pudo obtener el usuario desde Bitbucket");
        }

        return new User(
                dto.getId(),
                dto.getUsername(),
                dto.getName(),
                dto.getAvatarUrl(),
                dto.getWebUrl()
        );
    }
}



