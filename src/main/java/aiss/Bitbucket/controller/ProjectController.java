package aiss.Bitbucket.controller;

import aiss.Bitbucket.model.Dto.ProjectDto;
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
    public ProjectDto getProjectData() {
        return projectService.buildProjectDto();
    }


    @PostMapping
    public ResponseEntity<String> sendProjectData() {
        ProjectDto dto = projectService.buildProjectDto();

        String targetApiUrl = "http://localhost:8081/api/projects"; // Modifica según tu nueva API

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<ProjectDto> request = new HttpEntity<>(dto, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(targetApiUrl, request, String.class);

        return ResponseEntity.status(response.getStatusCode()).body("Proyecto enviado correctamente.");
    }
}
