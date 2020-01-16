package com.dtek.portal.ui.fragment.news.data;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.dtek.portal.api.IOnErrorListener;
import com.dtek.portal.models.news.News;
import com.dtek.portal.models.news.NewsList;

import retrofit2.Call;

public class NewsListRepo extends PageKeyedDataSource<Long, News> {

    private Call<NewsList> newsListCall;
    private INewsListRepo iNewsListRepo;
    private IOnErrorListener iOnErrorListener;

    public NewsListRepo(INewsListRepo iNewsListRepo, IOnErrorListener iOnErrorListener ){
        this.iNewsListRepo = iNewsListRepo;
        this.iOnErrorListener = iOnErrorListener;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Long> params, @NonNull LoadInitialCallback<Long, News> callback) {

    }

    @Override
    public void loadBefore(@NonNull LoadParams<Long> params, @NonNull LoadCallback<Long, News> callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Long> params, @NonNull LoadCallback<Long, News> callback) {

    }
}
