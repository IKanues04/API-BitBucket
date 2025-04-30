package aiss.Bitbucket.model.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserDto {

    @JsonProperty("uuid")
    private String id;

    @JsonProperty("username")
    private String username;

    @JsonProperty("display_name")
    private String name;

    @JsonProperty("links")
    private Links links;

    public static class Links {
        @JsonProperty("avatar")
        private Avatar avatar;

        @JsonProperty("html")
        private Html web;
    }

    public static class Avatar {
        @JsonProperty("href")
        private String avatar_url;
    }

    public static class Html {
        @JsonProperty("href")
        private String web_url;
    }

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatarUrl() {
        return links.avatar.avatar_url;
    }

    public void setAvatarUrl(String avatar_url) {
        this.links.avatar.avatar_url = avatar_url;
    }

    public String getWebUrl() {
        return links.web.web_url;
    }

    public void setWebUrl(String web_url) {
        this.links.web.web_url = web_url;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", avatar_url='" + links.avatar.avatar_url + '\'' +
                ", web_url='" + links.web.web_url + '\'' +
                '}';
    }
}
