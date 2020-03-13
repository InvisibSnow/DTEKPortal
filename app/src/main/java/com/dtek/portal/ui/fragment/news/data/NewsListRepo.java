package com.dtek.portal.ui.fragment.news.data;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.dtek.portal.api.IOnErrorListener;
import com.dtek.portal.api.RestManager;
import com.dtek.portal.models.news.News;
import com.dtek.portal.models.news.NewsList;
import com.dtek.portal.utils.Headers;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.dtek.portal.utils.Const.News.API_NEWS;

public class NewsListRepo extends PageKeyedDataSource<Long, News> implements INewsListRepo {

    private Call<NewsList> newsListCall;
    private INewsListRepo.OnFinishedListener iNewsListRepo;
    private IOnErrorListener iOnErrorListener;
    private String newsCategory;

    public NewsListRepo(INewsListRepo.OnFinishedListener iNewsListRepo, IOnErrorListener iOnErrorListener, String newsCategory) {
        this.iNewsListRepo = iNewsListRepo;
        this.iOnErrorListener = iOnErrorListener;
        this.newsCategory = newsCategory;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Long> params, @NonNull LoadInitialCallback<Long, News> callback) {

        cancelCall(newsListCall);
        newsListCall = RestManager.getApi().getListNews(Headers.getAuthorityMap(), API_NEWS + 1 + newsCategory);
        newsListCall.enqueue(new Callback<NewsList>() {
            @Override
            public void onResponse(@NonNull Call<NewsList> call, @NonNull Response<NewsList> response) {
                if (response.isSuccessful() && response.body() != null) {
                    NewsList newsList = response.body();
                    callback.onResult(newsList.getNewses(), null, (long) 2);
                    iNewsListRepo.onFinishedNewsListLoad(false);
                } else if (response.code() == 401) {
                    iOnErrorListener.errorToken();
                } else {
                    iOnErrorListener.onFailure(response.message());
                }
            }

            @Override
            public void onFailure(@NonNull Call<NewsList> call, Throwable t) {
                if (!call.isCanceled()) {
                    iOnErrorListener.onFailure(t);
                }
            }
        });

    }

    @Override
    public void loadBefore(@NonNull LoadParams<Long> params, @NonNull LoadCallback<Long, News> callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Long> params, @NonNull LoadCallback<Long, News> callback) {

        newsListCall = RestManager.getApi().getListNews(Headers.getAuthorityMap(), API_NEWS + params.key + newsCategory);
        newsListCall.enqueue(new Callback<NewsList>() {
            @Override
            public void onResponse(@NonNull Call<NewsList> call, @NonNull Response<NewsList> response) {
                if (response.isSuccessful() && response.body() != null) {
                    NewsList newsList = response.body();
                    callback.onResult(newsList.getNewses(), params.key + 1);
                    iNewsListRepo.onFinishedNewsListLoad(false);
                } else if (response.code() == 401) {
                    iOnErrorListener.errorToken();
                } else {
                    iOnErrorListener.onFailure(response.message());
                }
            }

            @Override
            public void onFailure(@NonNull Call<NewsList> call, Throwable t) {
                if (!call.isCanceled()) {
                    iOnErrorListener.onFailure(t);
                }
            }
        });
    }

    private void cancelCall(Call call) {
        if (call != null) {
            call.cancel();
        }
    }

    @Override
    public void cancelAllRequest() {
        cancelCall(newsListCall);
    }
}
