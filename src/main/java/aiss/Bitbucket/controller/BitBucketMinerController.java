package aiss.Bitbucket.controller;

import aiss.Bitbucket.model.Project;
import aiss.Bitbucket.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/bitbucketminer")
public class BitBucketMinerController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/{workspace}/{repo_slug}")
    public Project getProjectData( @PathVariable String workspace,
                                   @PathVariable String repo_slug,
                                   @RequestParam(required = false, defaultValue = "5") int nCommits,
                                   @RequestParam(required = false, defaultValue = "5") int nIssues,
                                   @RequestParam(required = false, defaultValue = "2") int maxPages) {

        return projectService.buildProject(workspace, repo_slug, nCommits, nIssues, maxPages);
    }


    @PostMapping("/{workspace}/{repo_slug}")
    public ResponseEntity<String> sendProjectData(
            @PathVariable String workspace,
            @PathVariable String repo_slug,
            @RequestParam(defaultValue = "5") int nCommits,
            @RequestParam(defaultValue = "5") int nIssues,
            @RequestParam(defaultValue = "2") int maxPages) {
        try{

            Project dto = projectService.buildProject(workspace, repo_slug, nCommits, nIssues, maxPages);


            //HEADERS
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Project> request = new HttpEntity<>(dto, headers);

            // LINK A ENVIAR AL API GITMINER
            String targetApiUrl = "http://localhost:8080/gitminer/projects/recive";

            ResponseEntity<Project> response = restTemplate.postForEntity(targetApiUrl, request, Project.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                return ResponseEntity.ok("Datos reenviados a API B: " + response.getBody().toString());
            } else {
                // Si la respuesta no es exitosa, devolver un mensaje de error
                return ResponseEntity.status(response.getStatusCode()).body("Error al enviar datos a API B: " + response.getStatusCode());
            }

        } catch (Exception e) {
            // Manejo de excepciones si algo sale mal
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al procesar la solicitud: " + e.getMessage());
            }
        }
}
