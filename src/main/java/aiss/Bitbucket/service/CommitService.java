package aiss.Bitbucket.service;

import aiss.Bitbucket.model.Commit;
import aiss.Bitbucket.model.Dto.CommitDto;
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
public class CommitService {

    @Value("${token}")
    private String token;

    @Autowired
    private RestTemplate restTemplate;

    private final String baseUri = "https://api.bitbucket.org/2.0/repositories/";

    public Commit getCommit (String workspace, String repo_slug, String commit) {
        String uri = baseUri + workspace + "/" + repo_slug + "/commit/" + commit;

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        headers.set("Accept", "application/json");
        HttpEntity<String> request = new HttpEntity<>(null, headers);

        ResponseEntity<CommitDto> response = restTemplate.exchange(
                uri, HttpMethod.GET, request, CommitDto.class
        );

        CommitDto dto = response.getBody();

        return new Commit(
                dto.getHash(),
                dto.getMessage(),
                dto.getMessage(),
                dto.getAuthor().getUser().getDisplay_name(),
                dto.getAuthor().getRaw(),
                dto.getDate(),
                dto.getLinks().getHtml().getHref()
        );

    }

}
