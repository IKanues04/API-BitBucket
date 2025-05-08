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

    // workspace = "snakeyaml";
    // repo = "snakeyaml";

    // METODO GET PARA OBTENER INFO DEL PROYECTO Y CREARLO
    public Project getProjectInfo(String workspace, String repo) {
        String url = apiUrl + "/repositories/" + workspace + "/" + repo;
        ResponseEntity<JsonNode> response = restTemplate.getForEntity(url, JsonNode.class);
        JsonNode body = response.getBody();

        Project info = new Project();
        info.setId(body.path("uuid").asText());
        info.setName(body.path("name").asText());
        info.setWeb_url(body.path("links").path("html").path("href").asText());
        return info;
    }

    // USO EL JsonNode CUANDO NO SE EXACTAMENTE LA ESTRUCTURA POJO
    public List<Commit> getCommits(String workspace, String repo) {
        String url = apiUrl + "/repositories/" + workspace + "/" + repo + "/commits";
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
            commit.setAuthor_name(node.path("author").path("user").path("display_name").asText("Revisa en el authorEmail ya que ahi esta tanto el nombre como el correo, nose cosas de BitBucket"));
            commit.setAuthor_email(node.path("author").path("raw").asText(""));
            commit.setAuthored_date(node.path("date").asText(""));
            commit.setWeb_url(node.path("links").path("html").path("href").asText(""));
            commits.add(commit);
        }
        return commits;
    }


    public List<Issue> getIssues(String workspace, String repo) {
        String url = apiUrl + "/repositories/" + workspace + "/" + repo + "/issues";
        List<Issue> issues = new ArrayList<>();

        try {
            ResponseEntity<JsonNode> response = restTemplate.getForEntity(url, JsonNode.class);
            JsonNode values = response.getBody().path("values");

            for (JsonNode node : values) {
                Issue issue = new Issue();
                issue.setId(node.path("id").asText());
                issue.setTitle(node.path("title").asText());
                issue.setDescription(node.path("content").path("raw").asText());
                issue.setState(node.path("state").asText());
                issue.setCreated_at(node.path("created_on").asText());
                issue.setUpdated_at(node.path("updated_on").asText());
                issue.setClosed_at(node.path("closed_on").asText("No resuelto"));
                issue.setVotes(node.path("votes").asInt());

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
                    assignee.setUsername(node.path("assignee").path("username").asText("No hay username"));
                    assignee.setName(node.path("assignee").path("display_name").asText());
                    assignee.setWeb_url(node.path("assignee").path("links").path("html").path("href").asText());
                    issue.setAssignee(assignee);
                }

                // Reporter
                if (node.has("reporter") && !node.path("reporter").isNull()) {
                    User reporter = new User();
                    reporter.setId(node.path("reporter").path("uuid").asText());
                    reporter.setUsername(node.path("reporter").path("username").asText("No hay username"));
                    reporter.setName(node.path("reporter").path("display_name").asText());
                    reporter.setWeb_url(node.path("reporter").path("links").path("html").path("href").asText());
                    issue.setAuthor(reporter);
                }
                // Comentarios
                issue.setComments(getComments(workspace, repo, issue.getId()));

                issues.add(issue);
            }
        } catch (HttpClientErrorException.NotFound e) {
            String responseBody = e.getResponseBodyAsString();
            if (responseBody.contains("Repository has no issue tracker")) {
                // Tracker desactivado, devolvemos lista vacía
                System.out.println("Issue tracker desactivado para: " + repo);
                return Collections.emptyList();
            } else {
                // Otro error, lo lanzamos
                throw e;
            }
        }

        return issues;
    }



    public List<Comment> getComments(String workspace, String repo, String issueId) {
        String url = apiUrl + "/repositories/" + workspace + "/" + repo + "/issues/" + issueId + "/comments";
        ResponseEntity<JsonNode> response = restTemplate.getForEntity(url, JsonNode.class);
        JsonNode values = response.getBody().path("values");

        List<Comment> comments = new ArrayList<>();
        for (JsonNode node : values) {
            Comment comment = new Comment();
            comment.setId(node.path("id").asText());
            comment.setBody(node.path("content").path("raw").asText("No hay contenido"));
            comment.setCreated_at(node.path("created_on").asText());
            comment.setUpdated_at(node.path("updated_on").asText("No actualizado"));

            // Autor
            if (node.has("user") && !node.path("user").isNull()) {
                User author = new User();
                author.setId(node.path("user").path("uuid").asText());
                author.setUsername(node.path("user").path("username").asText("No hay username"));
                author.setName(node.path("user").path("display_name").asText());
                author.setWeb_url(node.path("user").path("links").path("html").path("href").asText());
                comment.setAuthor(author);
            }

            comments.add(comment);
        }
        return comments;
    }

}
