package com.dtek.portal.ui.fragment.news;

import com.dtek.portal.ui.adapter.news.NewsCompanyListAdapter;
import com.dtek.portal.ui.adapter.news.NewsListAdapter;

import static com.dtek.portal.utils.Const.News.NEWS_CAT_COMPANY;

public class NewsCompanyListFragment extends NewsDtekListFragment {

    @Override
    public NewsListAdapter getNewsListAdapter() {
        return new NewsCompanyListAdapter();
    }

    @Override
    public String getNewsCategory() {
        return NEWS_CAT_COMPANY;
    }
}
