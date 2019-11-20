package com.sablania.githubtrendingrepo.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sablania.githubtrendingrepo.R;
import com.sablania.githubtrendingrepo.modelClasses.TrendingRepo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TrendingRepoAdapter extends RecyclerView.Adapter<TrendingRepoAdapter.ViewHolder> {

    private List<TrendingRepo> list = new ArrayList<>();

    LayoutInflater layoutInflater;

    public TrendingRepoAdapter(Context context) {
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(layoutInflater.inflate(R.layout.item_repo, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setUpView(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public List<TrendingRepo> getList() {
        return list;
    }

    public void setList(List<TrendingRepo> newList) {
        list.clear();
        list.addAll(newList);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.avatar)
        ImageView avatar;
        @BindView(R.id.tv_username)
        TextView tvUsername;
        @BindView(R.id.tv_repo_name)
        TextView tvRepoName;
        @BindView(R.id.tv_description)
        TextView tvDescription;
        @BindView(R.id.tv_language)
        TextView tvLanguage;
        @BindView(R.id.tv_stars)
        TextView tvStars;
        @BindView(R.id.tv_forks)
        TextView tvForks;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setUpView(TrendingRepo item) {
            if (!item.getBuiltBy().isEmpty()) {
                tvUsername.setText(item.getBuiltBy().get(0).getUsername());

            }
        }
    }
}
