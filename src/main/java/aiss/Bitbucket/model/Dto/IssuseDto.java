package aiss.Bitbucket.model.Dto;

import aiss.Bitbucket.model.User;

public class IssuseDto {
    // ATRIBUTOS
    private String type;
    private Links links;
    private int id;
    private Repository repository;
    private String title;
    private User reporter;
    private User assignee;
    private String created_on;
    private String updated_on;
    private String edited_on;
    private String state;
    private String kind;
    private String priority;
    private Milestone milestone;
    private Version version;
    private Component component;
    private int votes;
    private Content content;

    // SUBCLASE LINKS
    public static class Links {
        private Link self;
        private Link html;
        private Link comments;
        private Link attachments;
        private Link watch;
        private Link vote;
    }

    // SUBCLASE LINK
    public static class Link {
        private String href;
        private String name;

        public String getHref() {
            return href;
        }

        public void setHref(String href) {
            this.href = href;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    // SUBCLASE REPOSITORY
    public static class Repository {
        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        private String type;
    }

    // SUBCLASE MILESTONE
    public static class Milestone {
        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        private String type;
    }

    // SUBCLASE VERSION
    public static class Version {
        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        private String type;
    }

    // SUBCLASE COMPONENT
    public static class Component {
        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        private String type;
    }

    // SUBCLASE CONTENT
    public static class Content {
        private String raw;
        private String markup;

        public String getHtml() {
            return html;
        }

        public void setHtml(String html) {
            this.html = html;
        }

        public String getMarkup() {
            return markup;
        }

        public void setMarkup(String markup) {
            this.markup = markup;
        }

        public String getRaw() {
            return raw;
        }

        public void setRaw(String raw) {
            this.raw = raw;
        }

        private String html;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Repository getRepository() {
        return repository;
    }

    public void setRepository(Repository repository) {
        this.repository = repository;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getReporter() {
        return reporter;
    }

    public void setReporter(User reporter) {
        this.reporter = reporter;
    }

    public User getAssignee() {
        return assignee;
    }

    public void setAssignee(User assignee) {
        this.assignee = assignee;
    }

    public String getCreated_on() {
        return created_on;
    }

    public void setCreated_on(String created_on) {
        this.created_on = created_on;
    }

    public String getUpdated_on() {
        return updated_on;
    }

    public void setUpdated_on(String updated_on) {
        this.updated_on = updated_on;
    }

    public String getEdited_on() {
        return edited_on;
    }

    public void setEdited_on(String edited_on) {
        this.edited_on = edited_on;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public Milestone getMilestone() {
        return milestone;
    }

    public void setMilestone(Milestone milestone) {
        this.milestone = milestone;
    }

    public Version getVersion() {
        return version;
    }

    public void setVersion(Version version) {
        this.version = version;
    }

    public Component getComponent() {
        return component;
    }

    public void setComponent(Component component) {
        this.component = component;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }
}
