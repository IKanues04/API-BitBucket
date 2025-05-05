package aiss.Bitbucket.service;

import aiss.Bitbucket.model.Comment;
import aiss.Bitbucket.model.Commit;
import aiss.Bitbucket.model.Dto.ProjectDto;
import aiss.Bitbucket.model.Issue;
import aiss.Bitbucket.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class ProjectService {

    @Autowired
    private BitBucketService bitbucketService;

    public ProjectDto buildProjectDto() {
        ProjectDto dto = new ProjectDto();
        Project info = bitbucketService.getProjectInfo();

        dto.setId(info.getId());
        dto.setName(info.getName());
        dto.setWebUrl(info.getWebUrl());
        dto.setCommits(bitbucketService.getCommits());
        dto.setIssues(bitbucketService.getIssues());
        return dto;
    }

    /*
    // FUNCIONES AUXILIARES
    private ProjectDto.CommitInfo mapToCommitInfo(Commit commit) {
        ProjectDto.CommitInfo dto = new ProjectDto.CommitInfo();
        dto.setId(commit.getId());
        dto.setTitle(commit.getTitle());
        dto.setMessage(commit.getMessage());
        dto.setAuthor(commit.getAuthorName());
        dto.setDate(commit.getAuthoredDate());
        return dto;
    }

    private ProjectDto.IssueInfo mapToIssueInfo(Issue issue) {
        ProjectDto.IssueInfo dto = new ProjectDto.IssueInfo();
        dto.setId(issue.getId());
        dto.setTitle(issue.getTitle());
        dto.setDescription(issue.getDescription());
        dto.setState(issue.getState());
        dto.setCreatedAt(issue.getCreatedAt());
        dto.setUpdatedAt(issue.getUpdatedAt());
        dto.setClosedAt(issue.getClosedAt());
        dto.setVotes(issue.getVotes());
        dto.setLabels(issue.getLabels());

        // Asignado (puede ser null)
        dto.setAssignee(issue.getAssignee() != null ? issue.getAssignee().getName() : null);

        // Comentarios
        dto.setComments(issue.getComments().stream()
                .map(this::mapToCommentInfo)
                .collect(Collectors.toList()));

        return dto;
    }

    private ProjectDto.CommentInfo mapToCommentInfo(Comment comment) {
        ProjectDto.CommentInfo dto = new ProjectDto.CommentInfo();
        dto.setId(comment.getId());
        dto.setBody(comment.getBody());
        dto.setCreatedAt(comment.getCreatedAt());
        dto.setUpdatedAt(comment.getUpdatedAt());

        // Autor (puede ser null)
        dto.setAuthor(comment.getAuthor() != null ? comment.getAuthor().getName() : null);

        return dto;
    }

     */




}
