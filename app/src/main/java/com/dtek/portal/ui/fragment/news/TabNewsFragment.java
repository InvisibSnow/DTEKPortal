package com.dtek.portal.ui.fragment.news;

import com.dtek.portal.R;
import com.dtek.portal.ui.adapter.BasePagerAdapter;
import com.dtek.portal.ui.fragment.BaseTabFragment;

import static androidx.fragment.app.FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT;

public class TabNewsFragment extends BaseTabFragment {

    private NewsDtekListFragment newsDtekListFragment = new NewsDtekListFragment();
    private NewsCompanyListFragment newsCompanyListFragment;
    private NewsFavoritesListFragment newsFavoritesListFragment;

    @Override
    protected void initFragments() {

    }

    @Override
    protected void setupViewPager() {
       if(!isAdded()) return;

        BasePagerAdapter basePagerAdapter = new BasePagerAdapter(getChildFragmentManager(), 1);
        basePagerAdapter.addFragment(newsDtekListFragment, getString(R.string.title_news_dtek));
//        basePagerAdapter.addFragment(newsCompanyListFragment, getString(R.string.title_news_company));
//        basePagerAdapter.addFragment(newsFavoritesListFragment, getString(R.string.title_news_bookmark));

        mViewPager.setOffscreenPageLimit(2);
        mViewPager.setAdapter(basePagerAdapter);

        initTabLayout();
    }
}
