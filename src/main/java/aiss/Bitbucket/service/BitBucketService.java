package aiss.Bitbucket.service;

import aiss.Bitbucket.model.*;
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

    private final String workspace = "snakeyaml";
    private final String repoSlug = "snakeyaml";

    // METODO GET PARA OBTENER INFO DEL PROYECTO Y CREARLO
    public Project getProjectInfo() {
        String url = apiUrl + "/repositories/" + workspace + "/" + repoSlug;
        ResponseEntity<JsonNode> response = restTemplate.getForEntity(url, JsonNode.class);
        JsonNode body = response.getBody();

        Project info = new Project();
        info.setId(body.path("uuid").asText());
        info.setName(body.path("name").asText());
        info.setWebUrl(body.path("links").path("html").path("href").asText());
        return info;
    }

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
                    labels.add(node.path("kind").asText());
                }
                issue.setLabels(labels);

                // Asignado
                if (node.has("assignee") && !node.path("assignee").isNull()) {
                    User assignee = new User();
                    assignee.setId(node.path("assignee").path("uuid").asText());
                    assignee.setUsername(node.path("assignee").path("username").asText());
                    assignee.setName(node.path("assignee").path("display_name").asText());
                    assignee.setWebUrl(node.path("assignee").path("links").path("html").path("href").asText());
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
        String url = apiUrl + "/repositories/" + workspace + "/" + repoSlug + "/issues/" + issueId + "/comments";
        ResponseEntity<JsonNode> response = restTemplate.getForEntity(url, JsonNode.class);
        JsonNode values = response.getBody().get("values");

        List<Comment> comments = new ArrayList<>();
        for (JsonNode node : values) {
            Comment comment = new Comment();
            comment.setId(node.path("id").asText());
            comment.setBody(node.path("content").path("raw").asText());
            comment.setCreatedAt(node.path("created_on").asText());
            comment.setUpdatedAt(node.path("updated_on").asText());

            // Autor
            if (node.has("user") && !node.path("user").isNull()) {
                User author = new User();
                author.setId(node.path("user").path("uuid").asText());
                author.setUsername(node.path("user").path("username").asText());
                author.setName(node.path("user").path("display_name").asText());
                author.setWebUrl(node.path("user").path("links").path("html").path("href").asText());
                comment.setAuthor(author);
            }

            comments.add(comment);
        }
        return comments;
    }

}
