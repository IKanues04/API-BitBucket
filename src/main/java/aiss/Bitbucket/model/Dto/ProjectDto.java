package aiss.Bitbucket.model.Dto;

public class ProjectDto {
    // ATRIBUTOS
    private String type;
    private User owner;
    private Workspace workspace;
    private String key;
    private String uuid;
    private boolean is_private;
    private String name;
    private String description;
    private ProjectLinks links;
    private String created_on;
    private String updated_on;
    private boolean has_publicly_visible_repos;

    // Getters and setters


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Workspace getWorkspace() {
        return workspace;
    }

    public void setWorkspace(Workspace workspace) {
        this.workspace = workspace;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public boolean isIs_private() {
        return is_private;
    }

    public void setIs_private(boolean is_private) {
        this.is_private = is_private;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ProjectLinks getLinks() {
        return links;
    }

    public void setLinks(ProjectLinks links) {
        this.links = links;
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

    public boolean isHas_publicly_visible_repos() {
        return has_publicly_visible_repos;
    }

    public void setHas_publicly_visible_repos(boolean has_publicly_visible_repos) {
        this.has_publicly_visible_repos = has_publicly_visible_repos;
    }

    // SUBCLASE USER
    public static class User {
        private String display_name;
        private UserLinks links;
        private String type;
        private String uuid;
        private String account_id;
        private String nickname;

        // Getters and setters

        public String getDisplay_name() {
            return display_name;
        }

        public void setDisplay_name(String display_name) {
            this.display_name = display_name;
        }

        public UserLinks getLinks() {
            return links;
        }

        public void setLinks(UserLinks links) {
            this.links = links;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }

        public String getAccount_id() {
            return account_id;
        }

        public void setAccount_id(String account_id) {
            this.account_id = account_id;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }
    }

    // SUBCLASE USERLINKS
    public static class UserLinks {
        private Link self;
        private Link avatar;
        private Link html;

        // Getters and setters

        public Link getSelf() {
            return self;
        }

        public void setSelf(Link self) {
            this.self = self;
        }

        public Link getAvatar() {
            return avatar;
        }

        public void setAvatar(Link avatar) {
            this.avatar = avatar;
        }

        public Link getHtml() {
            return html;
        }

        public void setHtml(Link html) {
            this.html = html;
        }
    }

    // SUBCLASE WORKSPACE
    public static class Workspace {
        private String type;
        private String uuid;
        private String name;
        private String slug;
        private WorkspaceLinks links;

        // Getters and setters

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSlug() {
            return slug;
        }

        public void setSlug(String slug) {
            this.slug = slug;
        }

        public WorkspaceLinks getLinks() {
            return links;
        }

        public void setLinks(WorkspaceLinks links) {
            this.links = links;
        }
    }

    // SUBCLASE WORKSPACE LINKS
    public static class WorkspaceLinks {
        private Link avatar;
        private Link html;
        private Link self;

        // Getters and setters

        public Link getAvatar() {
            return avatar;
        }

        public void setAvatar(Link avatar) {
            this.avatar = avatar;
        }

        public Link getHtml() {
            return html;
        }

        public void setHtml(Link html) {
            this.html = html;
        }

        public Link getSelf() {
            return self;
        }

        public void setSelf(Link self) {
            this.self = self;
        }
    }

    // SUBCLASE PROJECT LINKS
    public static class ProjectLinks {
        private Link self;
        private Link html;
        private Link repositories;
        private Link avatar;

        // Getters and setters

        public Link getSelf() {
            return self;
        }

        public void setSelf(Link self) {
            this.self = self;
        }

        public Link getHtml() {
            return html;
        }

        public void setHtml(Link html) {
            this.html = html;
        }

        public Link getRepositories() {
            return repositories;
        }

        public void setRepositories(Link repositories) {
            this.repositories = repositories;
        }

        public Link getAvatar() {
            return avatar;
        }

        public void setAvatar(Link avatar) {
            this.avatar = avatar;
        }
    }

    // SUBCLASE LINK
    public static class Link {
        private String href;

        // Getter and setter
        public String getHref() {
            return href;
        }

        public void setHref(String href) {
            this.href = href;
        }
    }


}

