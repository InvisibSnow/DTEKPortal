package com.dtek.portal.ui.fragment.news;

import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.dtek.portal.models.news.News;
import com.dtek.portal.mvvm.MyBindingFragment;
import com.dtek.portal.mvvm.MyFragmentViewModel;
import com.dtek.portal.ui.adapter.news.NewsListAdapter;
import com.dtek.portal.ui.fragment.news.data.INewsListRepo;
import com.dtek.portal.ui.fragment.news.data.NewsDataSourceFactory;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class NewsDtekListFragmentVM extends MyFragmentViewModel implements INewsListRepo {

    private NewsListAdapter newsListAdapter;

    @SuppressWarnings("unchecked")
    public NewsDtekListFragmentVM(MyBindingFragment fragment) {
        super(fragment);
        initPaging(fragment);
    }

    private void initPaging(MyBindingFragment fragment){

        NewsDataSourceFactory newsDataSourceFactory = new NewsDataSourceFactory(this, getOnErrorListener());

        PagedList.Config config = (new PagedList.Config.Builder())
                .setEnablePlaceholders(true)
                .setInitialLoadSizeHint(10)
                .setPageSize(20)
                .setPrefetchDistance(4)
                .build();
        Executor executor = Executors.newFixedThreadPool(5);
        LiveData<PagedList<News>> pagedListLiveData = (new LivePagedListBuilder<Long, News>(newsDataSourceFactory, config))
                .setFetchExecutor(executor)
                .build();

        newsListAdapter = new NewsListAdapter();
        pagedListLiveData.observe(fragment, users -> {
            newsListAdapter.submitList(users);
        });

    }

    public void refresh(){}

    @Override
    public void onFinishedNewsListLoad(Boolean load) {

    }

    @Override
    public void cancelAllRequest() {

    }
}
