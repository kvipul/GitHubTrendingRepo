package com.sablania.githubtrendingrepo.repositories;

import android.content.SharedPreferences;

import androidx.lifecycle.MutableLiveData;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sablania.githubtrendingrepo.GitHubTrendingRepo;
import com.sablania.githubtrendingrepo.modelClasses.TrendingRepo;
import com.sablania.githubtrendingrepo.utils.Api;
import com.sablania.githubtrendingrepo.utils.Constants;

import org.json.JSONArray;

import java.util.List;

public class TrendingRepoRepository {
    private static final long CACHE_EXPIRY_TIMER = 1000 * 60 * 60 * 2; //2 HOURS

    private MutableLiveData<List<TrendingRepo>> trendingRepoLiveData;

    //boolean: whether data has fetched successfully
    private MutableLiveData<Boolean> errorLiveData;
    private SharedPreferences appPref;

    public TrendingRepoRepository() {
        appPref = GitHubTrendingRepo.get().getSharedPreferences(Constants.APP_PREF, 0);
    }

    public MutableLiveData<List<TrendingRepo>> getTrendingRepoLiveData() {
        if (trendingRepoLiveData == null) {
            trendingRepoLiveData = new MutableLiveData<>();
        }
        return trendingRepoLiveData;
    }

    public MutableLiveData<Boolean> getErrorLiveData() {
        if (errorLiveData == null) {
            errorLiveData = new MutableLiveData<>();
        }
        return errorLiveData;
    }

    public void fetchTrendingRepoDataFromNetwork() {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, Api.GET_TRENDING_REPO, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                List<TrendingRepo> resp = new Gson().fromJson(response.toString(),
                        new TypeToken<List<TrendingRepo>>() {
                        }.getType());
                trendingRepoLiveData.postValue(resp);
                errorLiveData.postValue(false);
                setTrendingRepoDataToCache(resp);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                errorLiveData.postValue(true);
            }
        });
        Volley.newRequestQueue(GitHubTrendingRepo.get()).add(jsonArrayRequest);
    }


    public List<TrendingRepo> getTrendingRepoDataFromCache() {
        String data = appPref.getString(Constants.TRENDING_REPO_LIST, null);
        long cacheTime = appPref.getLong(Constants.CACHE_TIME, -1);

        if (data == null || System.currentTimeMillis() - cacheTime > CACHE_EXPIRY_TIMER) {
            return null;
        } else {
            return new Gson().fromJson(data, new TypeToken<List<TrendingRepo>>() {
            }.getType());
        }
    }

    private void setTrendingRepoDataToCache(List<TrendingRepo> list) {
        SharedPreferences.Editor editor = appPref.edit();
        editor.putString(Constants.TRENDING_REPO_LIST, new Gson().toJson(list));
        editor.putLong(Constants.CACHE_TIME, System.currentTimeMillis());
        editor.apply();
    }

    public void getTrendingRepoData() {
        List<TrendingRepo> trendingRepoDataFromCache = getTrendingRepoDataFromCache();
        if (trendingRepoDataFromCache == null) {
            fetchTrendingRepoDataFromNetwork();
        } else {
            trendingRepoLiveData.postValue(trendingRepoDataFromCache);
            errorLiveData.postValue(false);
        }
    }

}
