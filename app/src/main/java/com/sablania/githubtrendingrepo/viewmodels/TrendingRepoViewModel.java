package com.sablania.githubtrendingrepo.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sablania.githubtrendingrepo.modelClasses.TrendingRepo;
import com.sablania.githubtrendingrepo.repositories.TrendingRepoRepository;

import java.util.List;

public class TrendingRepoViewModel extends ViewModel {
    private TrendingRepoRepository trendingRepoRepository;

    public TrendingRepoViewModel() {
        trendingRepoRepository = new TrendingRepoRepository();
    }

    public MutableLiveData<List<TrendingRepo>> getTrendingRepoLiveData() {
        return trendingRepoRepository.getTrendingRepoLiveData();
    }

    public void makeRequestToGetTrendingRepo() {
        trendingRepoRepository.makeRequestToGetTrendingRepo();
    }
}
