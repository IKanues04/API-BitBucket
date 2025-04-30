package aiss.Bitbucket.model.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CommitDto {

    @JsonProperty("hash")
    private String hash; // ID

    @JsonProperty("message")
    private String message;

    @JsonProperty("date")
    private String date;

    @JsonProperty("author")
    private Author author;

    @JsonProperty("repository")
    private Repository repository;

    @JsonProperty("summary")
    private Summary summary;

    @JsonProperty("links")
    private Links links;

    // Subclases


    public static class Author {
        @JsonProperty("raw")
        private String raw;

        @JsonProperty("type")
        private String type;

        @JsonProperty("user")
        private User user;


        public static class User {

            @JsonProperty("desplay_name")
            private String display_name;

            @JsonProperty("uuid")
            private String uuid;

            @JsonProperty("type")
            private String type;

            @JsonProperty("nickname")
            private String nickname;

            @JsonProperty("account_id")
            private String account_id;

            @JsonProperty("links")
            private UserLinks links;

            public static class UserLinks {

                @JsonProperty("self")
                private Link self;

                @JsonProperty("html")
                private Link html;

                @JsonProperty("avatar")
                private Link avatar;

                // Getters y setters UserLinks

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

                public Link getAvatar() {
                    return avatar;
                }

                public void setAvatar(Link avatar) {
                    this.avatar = avatar;
                }
            }

            // Getters y setters User

            public String getDisplay_name() {
                return display_name;
            }

            public void setDisplay_name(String display_name) {
                this.display_name = display_name;
            }

            public String getUuid() {
                return uuid;
            }

            public void setUuid(String uuid) {
                this.uuid = uuid;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getAccount_id() {
                return account_id;
            }

            public void setAccount_id(String account_id) {
                this.account_id = account_id;
            }

            public UserLinks getLinks() {
                return links;
            }

            public void setLinks(UserLinks links) {
                this.links = links;
            }
        }

        // Getters y setters Author

        public String getRaw() {
            return raw;
        }

        public void setRaw(String raw) {
            this.raw = raw;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }
    }


    public static class Repository {

        @JsonProperty("name")
        private String name;
        @JsonProperty("type")
        private String type;
        @JsonProperty("full_name")
        private String full_name;
        @JsonProperty("uuid")
        private String uuid;
        @JsonProperty("links")
        private RepoLinks links;


        public static class RepoLinks {
            @JsonProperty("self")
            private Link self;
            @JsonProperty("html")
            private Link html;
            @JsonProperty("avatar")
            private Link avatar;

            // Getters y setters RepoLinks

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

            public Link getAvatar() {
                return avatar;
            }

            public void setAvatar(Link avatar) {
                this.avatar = avatar;
            }
        }

        // Getters y setters Repository

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getFull_name() {
            return full_name;
        }

        public void setFull_name(String full_name) {
            this.full_name = full_name;
        }

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }

        public RepoLinks getLinks() {
            return links;
        }

        public void setLinks(RepoLinks links) {
            this.links = links;
        }
    }


    public static class Summary {
        @JsonProperty("raw")
        private String raw;
        @JsonProperty("markup")
        private String markup;
        @JsonProperty("html")
        private String html;
        @JsonProperty("type")
        private String type;

        // Getters y setters Summary

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

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }


    public static class Links {
        @JsonProperty("self")
        private Link self;
        @JsonProperty("comments")
        private Link comments;
        @JsonProperty("patch")
        private Link patch;
        @JsonProperty("html")
        private Link html;
        @JsonProperty("diff")
        private Link diff;
        @JsonProperty("approve")
        private Link approve;
        @JsonProperty("statuses")
        private Link statuses;

        // Getters y setters Links

        public Link getSelf() {
            return self;
        }

        public void setSelf(Link self) {
            this.self = self;
        }

        public Link getComments() {
            return comments;
        }

        public void setComments(Link comments) {
            this.comments = comments;
        }

        public Link getPatch() {
            return patch;
        }

        public void setPatch(Link patch) {
            this.patch = patch;
        }

        public Link getHtml() {
            return html;
        }

        public void setHtml(Link html) {
            this.html = html;
        }

        public Link getDiff() {
            return diff;
        }

        public void setDiff(Link diff) {
            this.diff = diff;
        }

        public Link getApprove() {
            return approve;
        }

        public void setApprove(Link approve) {
            this.approve = approve;
        }

        public Link getStatuses() {
            return statuses;
        }

        public void setStatuses(Link statuses) {
            this.statuses = statuses;
        }
    }



    public static class Link {
        @JsonProperty("href")
        private String href;
        @JsonProperty("name")
        private String name; // opcional

        // Getters y setters link

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

    // Getters y setters de CommitDto

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Repository getRepository() {
        return repository;
    }

    public void setRepository(Repository repository) {
        this.repository = repository;
    }

    public Summary getSummary() {
        return summary;
    }

    public void setSummary(Summary summary) {
        this.summary = summary;
    }

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }
}

