package com.dtek.portal.ui.fragment.news;

import com.dtek.portal.R;
import com.dtek.portal.ui.fragment.ViewPagerFragment;
import com.dtek.portal.ui.fragment.base.BaseTabFragment;

import java.util.ArrayList;
import java.util.List;

public class TabNewsFragment extends BaseTabFragment {

    private NewsDtekListFragment newsDtekListFragment;
    private NewsCompanyListFragment newsCompanyListFragment;
    private NewsFavoritesListFragment newsFavoritesListFragment;

    @Override
    protected List<ViewPagerFragment> getViewPagerFragmentList() {
        List<ViewPagerFragment> vpFragmentList = new ArrayList<>();

        newsDtekListFragment = new NewsDtekListFragment();
        newsCompanyListFragment = new NewsCompanyListFragment();
        newsFavoritesListFragment = new NewsFavoritesListFragment();


        vpFragmentList.add(new ViewPagerFragment(new NewsDtekListFragment(), getString(R.string.title_news_dtek)));
        vpFragmentList.add(new ViewPagerFragment(new NewsCompanyListFragment(), getString(R.string.title_news_company)));
        vpFragmentList.add(new ViewPagerFragment(new NewsFavoritesListFragment(), getString(R.string.title_news_bookmark)));
        return vpFragmentList;
    }

    @Override
    public void updateView() {
        newsDtekListFragment.refresh();
        newsCompanyListFragment.refresh();
    }
}
