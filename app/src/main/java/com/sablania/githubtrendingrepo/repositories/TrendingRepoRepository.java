package com.sablania.githubtrendingrepo.repositories;

import androidx.lifecycle.MutableLiveData;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;
import com.sablania.githubtrendingrepo.GitHubTrendingRepo;
import com.sablania.githubtrendingrepo.modelClasses.TrendingRepo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TrendingRepoRepository {
    private MutableLiveData<List<TrendingRepo>> trendingRepoLiveData;

    public MutableLiveData<List<TrendingRepo>> getTrendingRepoLiveData() {
        if (trendingRepoLiveData == null) {
            trendingRepoLiveData = new MutableLiveData<>();
        }
        return trendingRepoLiveData;
    }

    public void makeRequestToGetTrendingRepo() {
        JSONObject requestBody = new JSONObject();
        String url = "https://github-trending-api.now.sh/repositories"; //provide url here
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                List<TrendingRepo> resp = new Gson().fromJson(response.toString(),
                        new TypeToken<List<TrendingRepo>>() {
                        }.getType());
                trendingRepoLiveData.postValue(resp);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        Volley.newRequestQueue(GitHubTrendingRepo.get()).add(jsonArrayRequest);
    }
}
