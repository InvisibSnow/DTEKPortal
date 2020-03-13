package com.dtek.portal.ui.fragment.news;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.dtek.portal.ui.adapter.news.NewsListAdapter;

public class NewsDtekListModelFactory extends ViewModelProvider.NewInstanceFactory {

    private NewsDtekListFragment newsDtekListFragment;
    private NewsListAdapter newsListAdapter;
    private String newsCategory;

    public NewsDtekListModelFactory(NewsDtekListFragment newsDtekListFragment, NewsListAdapter newsListAdapter, String newsCategory) {
        super();
        this.newsDtekListFragment = newsDtekListFragment;
        this.newsListAdapter = newsListAdapter;
        this.newsCategory = newsCategory;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(NewsDtekListFragmentVM.class)) {
            return (T) new NewsDtekListFragmentVM(newsDtekListFragment, newsListAdapter, newsCategory);
        }
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}
