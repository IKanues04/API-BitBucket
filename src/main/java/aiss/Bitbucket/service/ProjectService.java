package aiss.Bitbucket.service;

import aiss.Bitbucket.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private BitBucketService bitbucketService;

    public Project buildProject() {
        Project project = new Project();
        Project info = bitbucketService.getProjectInfo();

        project.setId(info.getId());
        project.setName(info.getName());
        project.setWeb_url(info.getWeb_url());
        project.setCommits(bitbucketService.getCommits());
        project.setIssues(bitbucketService.getIssues());
        return project;
    }




}
