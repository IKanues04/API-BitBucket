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


@Service
public class UserService {

    @Value("${token}")
    private String token;

    @Autowired
    private RestTemplate restTemplate;

    private final String baseUri="https://api.bitbucket.org/2.0/users/";

        public User getUser(String username) {
            String uri = baseUri + username;

            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + token);
            headers.set("Accept", "application/json");
            HttpEntity<String> request = new HttpEntity<>(null, headers);

            ResponseEntity<UserDto> response = restTemplate.exchange(
                    uri, HttpMethod.GET, request, UserDto.class
            );

            UserDto dto = response.getBody();

            return new User(
                    dto.getId(),
                    dto.getUsername(),
                    dto.getName(),
                    dto.getAvatarUrl(),
                    dto.getWebUrl()
            );
        }
    }



