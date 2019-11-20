package com.sablania.githubtrendingrepo.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sablania.githubtrendingrepo.R;
import com.sablania.githubtrendingrepo.adapters.TrendingRepoAdapter;
import com.sablania.githubtrendingrepo.modelClasses.TrendingRepo;
import com.sablania.githubtrendingrepo.viewmodels.TrendingRepoViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class HomePageFragment extends Fragment {


    public static final String TAG = HomePageFragment.class.getSimpleName();

    @BindView(R.id.rv_trending_repo)
    RecyclerView rvTrendingRepo;

    private TrendingRepoAdapter adapter;
    private TrendingRepoViewModel trendingRepoViewModel;


    public static Fragment newInstance() {
        return new HomePageFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_page, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new TrendingRepoAdapter(getContext());
        rvTrendingRepo.setAdapter(adapter);
        rvTrendingRepo.setLayoutManager(new LinearLayoutManager(getContext()));

        trendingRepoViewModel = ViewModelProviders.of(this).get(TrendingRepoViewModel.class);

        trendingRepoViewModel.makeRequestToGetTrendingRepo();

        trendingRepoViewModel.getTrendingRepoLiveData().observe(this, new Observer<List<TrendingRepo>>() {
            @Override
            public void onChanged(List<TrendingRepo> trendingRepos) {
                adapter.setList(trendingRepos);
            }
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    public interface Listener {
        void onCallback();
    }
}
