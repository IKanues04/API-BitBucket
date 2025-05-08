package aiss.Bitbucket.service;

import aiss.Bitbucket.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private BitBucketService bitbucketService;

    public Project buildProject(String owner, String repo) {
        Project project = new Project();
        Project info = bitbucketService.getProjectInfo(owner, repo);

        project.setId(info.getId());
        project.setName(info.getName());
        project.setWeb_url(info.getWeb_url());
        project.setCommits(bitbucketService.getCommits(owner, repo));
        project.setIssues(bitbucketService.getIssues(owner, repo));
        return project;
    }




}
