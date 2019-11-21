package com.sablania.githubtrendingrepo.modelClasses;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TrendingRepo {
    @SerializedName("author")
    private String author;

    @SerializedName("name")
    private String name;

    @SerializedName("avatar")
    private String avatar;

    @SerializedName("url")
    private String url;

    @SerializedName("description")
    private String description;

    @SerializedName("language")
    private String language;

    @SerializedName("stars")
    private int stars;

    @SerializedName("forks")
    private int forks;

    @SerializedName("currentPeriodStars")
    private String currentPeriodStars;

    @SerializedName("builtBy")
    private List<User> builtBy;

    public String getAuthor() {
        return author;
    }

    public String getName() {
        return name;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getUrl() {
        return url;
    }

    public String getDescription() {
        return description;
    }

    public int getStars() {
        return stars;
    }

    public int getForks() {
        return forks;
    }

    public String getCurrentPeriodStars() {
        return currentPeriodStars;
    }

    public List<User> getBuiltBy() {
        return builtBy;
    }

    public String getLanguage() {
        return language;
    }
}
