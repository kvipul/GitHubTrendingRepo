package com.sablania.githubtrendingrepo;

import android.app.Application;

public class GitHubTrendingRepo extends Application {

    private static GitHubTrendingRepo gitHubTrendingRepo;

    @Override
    public void onCreate() {
        super.onCreate();
        gitHubTrendingRepo = this;
    }

    public static GitHubTrendingRepo get() {
        return gitHubTrendingRepo;
    }
}
