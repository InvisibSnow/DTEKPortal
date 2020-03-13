package com.dtek.portal.ui.adapter.news;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.library.baseAdapters.BR;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.dtek.portal.R;
import com.dtek.portal.models.news.News;

import java.util.Objects;

public class NewsListAdapter extends PagedListAdapter<News, NewsListAdapter.NewsViewHolder > {

    public NewsListAdapter(){
        super(News.CALLBACK);
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater inflater=LayoutInflater.from(viewGroup.getContext());
        ViewDataBinding binding= DataBindingUtil.inflate(inflater, getLayoutId(), viewGroup,false);

        final NewsListAdapter.NewsViewHolder holder = new NewsListAdapter.NewsViewHolder(binding);
//        holder.itemView.setOnClickListener(view -> ProfileActivity.open(viewGroup.getContext(), getItem(holder.getAdapterPosition())));
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        holder.bind(Objects.requireNonNull(getItem(position)));
    }

    public int getLayoutId(){
        return R.layout.item_news_dtek_list;
    }

    class NewsViewHolder extends RecyclerView.ViewHolder {

        private final ViewDataBinding binding;

        NewsViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(News news) {
            binding.setVariable(BR.item, news);
            binding.executePendingBindings();
        }
    }

}
