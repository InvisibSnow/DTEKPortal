package com.dtek.portal.ui.fragment.news.data;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.dtek.portal.api.IOnErrorListener;
import com.dtek.portal.ui.fragment.news.NewsDtekListFragmentVM;

public class NewsDataSourceFactory extends DataSource.Factory {

    private MutableLiveData<NewsListRepo> mutableLiveData;

    private NewsDtekListFragmentVM newsDtekListFragmentVM;
    private IOnErrorListener iOnErrorListener;

    public NewsDataSourceFactory(NewsDtekListFragmentVM newsDtekListFragmentVM, IOnErrorListener iOnErrorListener){
        this.newsDtekListFragmentVM = newsDtekListFragmentVM;
        this.iOnErrorListener = iOnErrorListener;
        mutableLiveData = new MutableLiveData<>();
    }

    @Override
    public DataSource create() {
        NewsListRepo newsListRepo = new NewsListRepo(newsDtekListFragmentVM, iOnErrorListener);
        mutableLiveData.postValue(newsListRepo);
        return newsListRepo;
    }

    public MutableLiveData<NewsListRepo> getMutableLiveData() {
        return mutableLiveData;
    }
}
