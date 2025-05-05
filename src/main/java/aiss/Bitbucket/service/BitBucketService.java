package aiss.Bitbucket.service;

import aiss.Bitbucket.model.Comment;
import aiss.Bitbucket.model.Commit;
import aiss.Bitbucket.model.Issue;
import aiss.Bitbucket.model.User;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class BitBucketService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${bitbucket.api.url}")
    private String apiUrl;

    private final String workspace = "jwalton";
    private final String repoSlug = "opup";

    // USO EL JsonNode CUANDO NO SE EXACTAMENTE LA ESTRUCTURA POJO
    public List<Commit> getCommits() {
        String url = apiUrl + "/repositories/" + workspace + "/" + repoSlug + "/commits";
        ResponseEntity<JsonNode> response = restTemplate.getForEntity(url, JsonNode.class);

        JsonNode body = response.getBody();
        System.out.println("Bitbucket Response Body (commits): " + body);

        if (body == null || !body.has("values")) {
            throw new RuntimeException("La respuesta no contiene el nodo 'values'. Revisa si la URL o el acceso son correctos.");
        }

        JsonNode values = body.get("values");

        List<Commit> commits = new ArrayList<>();
        for (JsonNode node : values) {
            Commit commit = new Commit();
            commit.setId(node.path("hash").asText(""));
            commit.setTitle(node.path("summary").path("raw").asText(""));
            commit.setMessage(node.path("message").asText(""));
            commit.setAuthorName(node.path("author").path("user").path("display_name").asText(""));
            commit.setAuthorEmail(node.path("author").path("raw").asText(""));
            commit.setAuthoredDate(node.path("date").asText(""));
            commit.setWebUrl(node.path("links").path("html").path("href").asText(""));
            commits.add(commit);
        }
        return commits;
    }


    public List<Issue> getIssues() {
        String url = apiUrl + "/repositories/" + workspace + "/" + repoSlug + "/issues";
        List<Issue> issues = new ArrayList<>();

        try {
            ResponseEntity<JsonNode> response = restTemplate.getForEntity(url, JsonNode.class);
            JsonNode values = response.getBody().get("values");

            for (JsonNode node : values) {
                Issue issue = new Issue();
                issue.setId(node.get("id").asText());
                issue.setTitle(node.get("title").asText());
                issue.setDescription(node.get("content").get("raw").asText());
                issue.setState(node.get("state").asText());
                issue.setCreatedAt(node.get("created_on").asText());
                issue.setUpdatedAt(node.get("updated_on").asText());
                issue.setClosedAt(node.get("closed_on") != null ? node.get("closed_on").asText() : null);
                issue.setVotes(node.get("votes").asInt());

                // Etiquetas
                List<String> labels = new ArrayList<>();
                if (node.has("kind")) {
                    labels.add(node.get("kind").asText());
                }
                issue.setLabels(labels);

                // Asignado
                if (node.has("assignee") && !node.get("assignee").isNull()) {
                    User assignee = new User();
                    assignee.setId(node.get("assignee").get("uuid").asText());
                    assignee.setUsername(node.get("assignee").get("username").asText());
                    assignee.setName(node.get("assignee").get("display_name").asText());
                    assignee.setWebUrl(node.get("assignee").get("links").get("html").get("href").asText());
                    issue.setAssignee(assignee);
                }

                // Comentarios
                issue.setComments(getComments(issue.getId()));

                issues.add(issue);
            }
        } catch (HttpClientErrorException.NotFound e) {
            String responseBody = e.getResponseBodyAsString();
            if (responseBody.contains("Repository has no issue tracker")) {
                // Tracker desactivado, devolvemos lista vacía
                System.out.println("Issue tracker desactivado para: " + repoSlug);
                return Collections.emptyList();
            } else {
                // Otro error, lo lanzamos
                throw e;
            }
        }

        return issues;
    }



    public List<Comment> getComments(String issueId) {
        String url = apiUrl + "/issues/" + issueId + "/comments";
        ResponseEntity<JsonNode> response = restTemplate.getForEntity(url, JsonNode.class);
        JsonNode values = response.getBody().get("values");

        List<Comment> comments = new ArrayList<>();
        for (JsonNode node : values) {
            Comment comment = new Comment();
            comment.setId(node.get("id").asText());
            comment.setBody(node.get("content").get("raw").asText());
            comment.setCreatedAt(node.get("created_on").asText());
            comment.setUpdatedAt(node.get("updated_on").asText());

            // Autor
            if (node.has("user") && !node.get("user").isNull()) {
                User author = new User();
                author.setId(node.get("user").get("uuid").asText());
                author.setUsername(node.get("user").get("username").asText());
                author.setName(node.get("user").get("display_name").asText());
                author.setWebUrl(node.get("user").get("links").get("html").get("href").asText());
                comment.setAuthor(author);
            }

            comments.add(comment);
        }
        return comments;
    }

}
