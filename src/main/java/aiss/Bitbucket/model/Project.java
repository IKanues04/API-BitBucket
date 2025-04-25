package aiss.Bitbucket.model;


import java.util.List;

public class Project {

    // ATRIBUTOS
    private String id;
    private String name;
    private String webUrl;

    private List<Issue> issues;
    private List<Commit> commits;

    // INICIALIZAR ATRIBUTOS

    public Project() {

    }

    public Project(String id, String name, String webUrl, List<Issue> issues, List<Commit> commits) {
        this.id = id;
        this.name = name;
        this.webUrl = webUrl;

        this.issues = issues;
        this.commits = commits;
    }

    // GETTERS Y SETTERS

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public List<Issue> getIssues() {
        return issues;
    }

    public void setIssues(List<Issue> issues) {
        this.issues = issues;
    }

    public List<Commit> getCommits() {
        return commits;
    }

    public void setCommits(List<Commit> commits) {
        this.commits = commits;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", webUrl='" + webUrl + '\'' +
                ", issues=" + issues +
                ", commits=" + commits +
                '}';
    }
}
