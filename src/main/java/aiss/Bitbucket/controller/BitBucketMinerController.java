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
    public Project getProjectData(@PathVariable String workspace, @PathVariable String repo_slug) {
        return projectService.buildProject(workspace, repo_slug);
    }


    @PostMapping("/{workspace}/{repo_slug}")
    public ResponseEntity<String> sendProjectData(@PathVariable String workspace, @PathVariable String repo_slug) {
        Project dto = projectService.buildProject(workspace, repo_slug);
        
        
        //HEADERS
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Project> request = new HttpEntity<>(dto, headers);
        
        // LINK A ENVIAR AL API GITMINER
        String targetApiUrl = "http://localhost:8080/gitminer/projects/recive";

        ResponseEntity<Project> response = restTemplate.postForEntity(targetApiUrl, request, Project.class);

        return ResponseEntity.ok("Datos reenviados a API B: " + response.getBody());
    }
}
