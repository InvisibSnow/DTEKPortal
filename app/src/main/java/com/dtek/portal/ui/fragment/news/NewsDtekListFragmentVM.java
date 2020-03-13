package com.dtek.portal.ui.fragment.news;

import android.util.Log;

import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;
import androidx.databinding.ObservableBoolean;
import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.dtek.portal.callback.PaginationScrollListener;
import com.dtek.portal.models.news.News;
import com.dtek.portal.mvvm.MyBindingFragment;
import com.dtek.portal.mvvm.MyFragmentViewModel;
import com.dtek.portal.ui.adapter.news.NewsListAdapter;
import com.dtek.portal.ui.fragment.news.data.INewsListRepo;
import com.dtek.portal.ui.fragment.news.data.NewsDataSourceFactory;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class NewsDtekListFragmentVM extends MyFragmentViewModel implements INewsListRepo.OnFinishedListener {

    private NewsListAdapter newsListAdapter;
    private String newsCategory;

    private NewsDataSourceFactory newsDataSourceFactory;
    private LiveData<PagedList<News>> pagedListLiveData;

    public final ObservableBoolean isLoadingFirst = new ObservableBoolean(true);
    public final ObservableBoolean isLoadingNext = new ObservableBoolean();

    @SuppressWarnings("unchecked")
    public NewsDtekListFragmentVM(MyBindingFragment fragment, NewsListAdapter newsListAdapter, String newsCategory ) {
        super(fragment);
        this.newsListAdapter = newsListAdapter;
        this.newsCategory = newsCategory;
        initPaging(fragment);
    }

    private void initPaging(MyBindingFragment fragment) {

        Log.d("MyLOG","initPaging");

        if(newsDataSourceFactory == null) {
            Log.d("MyLOG","newsDataSourceFactory  NULLL");
            newsDataSourceFactory = new NewsDataSourceFactory(this, getOnErrorListener(), newsCategory);

            PagedList.Config config = (new PagedList.Config.Builder())
                    .setEnablePlaceholders(true)
                    .setInitialLoadSizeHint(10)
                    .setPageSize(20)
                    .setPrefetchDistance(4)
                    .build();
            Executor executor = Executors.newFixedThreadPool(5);
            pagedListLiveData = (new LivePagedListBuilder<Long, News>(newsDataSourceFactory, config))
                    .setFetchExecutor(executor)
                    .build();

            pagedListLiveData.observe(fragment, news -> {
                newsListAdapter.submitList(news);
            });
        } else {
            Log.d("MyLOG","newsDataSourceFactory NOT NULLL");

        }
    }

    public void refresh() {
        newsListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFinishedNewsListLoad(Boolean load) {
        isLoadingNext.set(false);
        isLoadingFirst.set(false);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        newsDataSourceFactory.getNewsListRepo().cancelAllRequest();
        Log.d("MyLOG","FRAGMENT onCleared");
    }

    @Bindable
    public NewsListAdapter getNewsListAdapter() {
        return newsListAdapter;
    }

    @BindingAdapter("listener")
    public static void bind(RecyclerView recyclerView, final NewsDtekListFragmentVM viewModel){
        recyclerView.addOnScrollListener(new PaginationScrollListener((LinearLayoutManager) recyclerView.getLayoutManager()) {
            @Override
            protected void loadMoreItems() {
                viewModel.isLoadingNext.set(true);
            }

            @Override
            public int getTotalPageCount() {
                return 0;
            }

            @Override
            public boolean isLastPage() {
                return false;
            }

            @Override
            public boolean isLoading() {
                return false;
            }
        });
    }

    @BindingAdapter("refreshListener")
    public static void bind(SwipeRefreshLayout swipeRefreshLayout, final NewsDtekListFragmentVM viewModel){
        swipeRefreshLayout.setOnRefreshListener(() -> {
            swipeRefreshLayout.setRefreshing(false);
            viewModel.newsDataSourceFactory.invalidate();
            viewModel.isLoadingFirst.set(true);
        });
    }

    @Override
    public void stopLoading() {
        isLoadingNext.set(false);
        isLoadingFirst.set(false);
    }
}
