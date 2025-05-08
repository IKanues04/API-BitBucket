package aiss.Bitbucket.controller;

import aiss.Bitbucket.model.Project;
import aiss.Bitbucket.service.ProjectService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping
    public Project getProjectData() {
        return projectService.buildProject();
    }


    @PostMapping
    public ResponseEntity<String> sendProjectData() {
        Project dto = projectService.buildProject();
        
        
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
