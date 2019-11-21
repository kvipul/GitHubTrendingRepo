package com.sablania.githubtrendingrepo.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.sablania.githubtrendingrepo.R;
import com.sablania.githubtrendingrepo.modelClasses.TrendingRepo;
import com.sablania.githubtrendingrepo.modelClasses.User;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TrendingRepoAdapter extends RecyclerView.Adapter<TrendingRepoAdapter.ViewHolder> {

    LayoutInflater layoutInflater;
    private List<TrendingRepo> list = new ArrayList<>();
    private Context context;
    private int expendedPosition = -1;


    public TrendingRepoAdapter(Context context) {
        layoutInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(layoutInflater.inflate(R.layout.item_repo, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setUpView(list.get(position), position);
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
        @BindView(R.id.cl_details)
        ConstraintLayout clDetails;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setUpView(TrendingRepo item, int position) {
            if (!item.getBuiltBy().isEmpty()) {
                User user = item.getBuiltBy().get(0);
                Glide.with(context).load(user.getAvatar()).into(avatar);
                tvUsername.setText(user.getUsername());
            } else {
                tvUsername.setText(R.string.no_contributor);
            }
            tvRepoName.setText(item.getName());

            if (item.getDescription() == null || item.getDescription().trim().isEmpty()) {
                tvDescription.setVisibility(View.GONE);
            } else {
                tvDescription.setVisibility(View.VISIBLE);
                tvDescription.setText(item.getDescription());
            }
            tvStars.setText(String.valueOf(item.getStars()));
            tvForks.setText(String.valueOf(item.getStars()));

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int temp = expendedPosition;
                    if (position == expendedPosition) {
                        expendedPosition = -1;
                    } else {
                        expendedPosition = position;
                    }
                    notifyDataSetChanged();
//                    notifyItemChanged(temp);
//                    notifyItemChanged(expendedPosition);
                }
            });
//            if (position == expendedPosition) {
//                clDetails.setVisibility(View.VISIBLE);
//                Utils.expandViewWithAnimation(clDetails);
//            } else if(clDetails.getVisibility() == View.VISIBLE) {
//                Utils.collapseViewWithAnimation(clDetails);
//                clDetails.setVisibility(View.GONE);
//            }

        }
    }
}
