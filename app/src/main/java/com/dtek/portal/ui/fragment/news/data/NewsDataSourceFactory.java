package com.dtek.portal.ui.fragment.news.data;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.dtek.portal.api.IOnErrorListener;
import com.dtek.portal.ui.fragment.news.NewsDtekListFragmentVM;

public class NewsDataSourceFactory extends DataSource.Factory {

    private MutableLiveData<NewsListRepo> mutableLiveData;

    private NewsDtekListFragmentVM newsDtekListFragmentVM;
    private IOnErrorListener iOnErrorListener;
    private String newsCategory;

    private NewsListRepo newsListRepo;

    public NewsDataSourceFactory(NewsDtekListFragmentVM newsDtekListFragmentVM, IOnErrorListener iOnErrorListener, String newsCategory){
        this.newsDtekListFragmentVM = newsDtekListFragmentVM;
        this.iOnErrorListener = iOnErrorListener;
        this.newsCategory = newsCategory;
        mutableLiveData = new MutableLiveData<>();
    }

    @Override
    public DataSource create() {
        newsListRepo = new NewsListRepo(newsDtekListFragmentVM, iOnErrorListener, newsCategory);
        mutableLiveData.postValue(newsListRepo);
        return newsListRepo;
    }

    public MutableLiveData<NewsListRepo> getMutableLiveData() {
        return mutableLiveData;
    }

    public NewsListRepo getNewsListRepo() {
        return newsListRepo;
    }

    public void invalidate(){
        newsListRepo.invalidate();
    }
}
