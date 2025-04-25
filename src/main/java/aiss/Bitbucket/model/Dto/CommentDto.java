package aiss.Bitbucket.model.Dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class CommentDto {

    // ATRIBUTOS
    private String type;
    private Long id;
    private String created_on;
    private String updated_on;
    private Content content;
    private User user;
    private boolean deleted;
    private Inline inline;
    private Links links;
    private Commit commit;


    // CLASES ANIDADAS
    // CLASE "CONTENT"
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Content {

        // ATRIBUTOS
        private String raw;
        private String markup;
        private String html;

        // Getters and setters

        public String getRaw() {
            return raw;
        }

        public void setRaw(String raw) {
            this.raw = raw;
        }

        public String getMarkup() {
            return markup;
        }

        public void setMarkup(String markup) {
            this.markup = markup;
        }

        public String getHtml() {
            return html;
        }

        public void setHtml(String html) {
            this.html = html;
        }
    }

    // CLASE "USER"

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class User {
        private String type;
        // puedes extender esta clase si obtienes más campos

        // Getters and setters

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }

    // CLASE "INLINE"

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Inline {
        private int from;
        private int to;
        private String path;

        // Getters and setters

        public int getFrom() {
            return from;
        }

        public void setFrom(int from) {
            this.from = from;
        }

        public int getTo() {
            return to;
        }

        public void setTo(int to) {
            this.to = to;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }
    }

        // CLASE "LINKS"

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Links {
        private HrefObj self;
        private HrefObj html;
        private HrefObj code;

        // CLASE "HREFOBJ"
        public static class HrefObj {
            private String href;
            private String name;

            // Getters and setters

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

        // Getters and setters

        public HrefObj getSelf() {
            return self;
        }

        public void setSelf(HrefObj self) {
            this.self = self;
        }

        public HrefObj getHtml() {
            return html;
        }

        public void setHtml(HrefObj html) {
            this.html = html;
        }

        public HrefObj getCode() {
            return code;
        }

        public void setCode(HrefObj code) {
            this.code = code;
        }
    }

        // CLASE "COMMIT"

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Commit {
        private String type;

        // Getters and setters

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }


    // GETTERS Y SETTERS (CLASE PRINCIPAL)

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public Inline getInline() {
        return inline;
    }

    public void setInline(Inline inline) {
        this.inline = inline;
    }

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    public Commit getCommit() {
        return commit;
    }

    public void setCommit(Commit commit) {
        this.commit = commit;
    }
}