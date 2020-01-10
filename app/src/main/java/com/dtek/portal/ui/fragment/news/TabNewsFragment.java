package com.dtek.portal.ui.fragment.news;

import com.dtek.portal.R;
import com.dtek.portal.ui.fragment.ViewPagerFragment;
import com.dtek.portal.ui.fragment.base.BaseTabFragment;

import java.util.ArrayList;
import java.util.List;

public class TabNewsFragment extends BaseTabFragment {

    @Override
    protected List<ViewPagerFragment> getViewPagerFragmentList() {
        List<ViewPagerFragment> vpFragmentList = new ArrayList<>();

        vpFragmentList.add(new ViewPagerFragment(new NewsDtekListFragment(), getString(R.string.title_news_dtek)));
        vpFragmentList.add(new ViewPagerFragment(new NewsCompanyListFragment(), getString(R.string.title_news_company)));
        vpFragmentList.add(new ViewPagerFragment(new NewsFavoritesListFragment(), getString(R.string.title_news_bookmark)));
        return vpFragmentList;
    }

//    @Override
//    protected void initFragments() {
//    }
//
//    @Override
//    protected void setupViewPager() {
//       if(!isAdded()) return;
//
//        if (getActivity() != null) {
//            ((MainActivity) getActivity()).setTitle(getString(R.string.title_news));
//        }
//
//        BasePagerAdapter basePagerAdapter = new BasePagerAdapter(getChildFragmentManager(), 1);
//        basePagerAdapter.addFragment(newsDtekListFragment, getString(R.string.title_news_dtek));
////        basePagerAdapter.addFragment(newsCompanyListFragment, getString(R.string.title_news_company));
////        basePagerAdapter.addFragment(newsFavoritesListFragment, getString(R.string.title_news_bookmark));
//
//        mViewPager.setOffscreenPageLimit(2);
//        mViewPager.setAdapter(basePagerAdapter);
//
//        initTabLayout();
//    }
}
