package aiss.Bitbucket.controller;

import aiss.Bitbucket.model.Project;
import aiss.Bitbucket.service.ProjectService;
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
        System.out.println("Proyecto a enviar: " + dto); // COMPROBAR QUE NO ESTA VACIO

        String targetApiUrl = "http://localhost:8080/gitminer/projects"; // Modifica según tu nueva API

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Project> request = new HttpEntity<>(dto, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(targetApiUrl, request, String.class);

        return ResponseEntity.status(response.getStatusCode()).body("Proyecto enviado correctamente.");
    }
}
