package com.sablania.githubtrendingrepo.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.Group;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

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
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.cg_progress)
    Group cgProgress;
    @BindView(R.id.tv_retry)
    TextView tvRetry;
    @BindView(R.id.layout_error)
    View layoutError;

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

        showProgressUI();

        adapter = new TrendingRepoAdapter(getContext());
        rvTrendingRepo.setAdapter(adapter);
        rvTrendingRepo.setLayoutManager(new LinearLayoutManager(getContext()));

        DividerItemDecoration decoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        rvTrendingRepo.addItemDecoration(decoration);

        trendingRepoViewModel = ViewModelProviders.of(this).get(TrendingRepoViewModel.class);


        trendingRepoViewModel.getTrendingRepoLiveData().observe(this, new Observer<List<TrendingRepo>>() {
            @Override
            public void onChanged(List<TrendingRepo> trendingRepos) {
                adapter.setList(trendingRepos);
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        trendingRepoViewModel.getErrorLiveData().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isErrorOccurred) {
                if (isErrorOccurred) {
                    showErrorUI();
                } else {
                    showDataUI();
                }
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                trendingRepoViewModel.getTrendingRepoDataFromNetwork();
            }
        });

        tvRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                trendingRepoViewModel.getTrendingRepoData();
            }
        });

        trendingRepoViewModel.getTrendingRepoData();
    }

    private void showProgressUI() {
        cgProgress.setVisibility(View.VISIBLE);
        rvTrendingRepo.setVisibility(View.GONE);
        layoutError.setVisibility(View.GONE);
    }

    private void showDataUI() {
        cgProgress.setVisibility(View.GONE);
        rvTrendingRepo.setVisibility(View.VISIBLE);
        layoutError.setVisibility(View.GONE);
    }

    private void showErrorUI() {
        cgProgress.setVisibility(View.GONE);
        rvTrendingRepo.setVisibility(View.GONE);
        layoutError.setVisibility(View.VISIBLE);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    public interface Listener {
        void onCallback();
    }
}
