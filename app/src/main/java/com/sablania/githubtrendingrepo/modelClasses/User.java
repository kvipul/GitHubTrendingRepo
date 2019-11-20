package com.sablania.githubtrendingrepo.modelClasses;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("username")
    private String username;

    @SerializedName("href")
    private String href;

    @SerializedName("avatar")
    private String avatar;

    public String getUsername() {
        return username;
    }

    public String getHref() {
        return href;
    }

    public String getAvatar() {
        return avatar;
    }
}
