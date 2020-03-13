package com.dtek.portal.ui.fragment.hr_vocation;

import com.dtek.portal.R;
import com.dtek.portal.ui.fragment.ViewPagerFragment;
import com.dtek.portal.ui.fragment.base.BaseTabFragment;

import java.util.ArrayList;
import java.util.List;

public class TabHrVocationFragment extends BaseTabFragment {

    @Override
    protected List<ViewPagerFragment> getViewPagerFragmentList() {
        List<ViewPagerFragment> vpFragmentList = new ArrayList<>();

        HrVocationLimitFragment hrVocationLimitFragment = new HrVocationLimitFragment();

        vpFragmentList.add(new ViewPagerFragment(hrVocationLimitFragment, getString(R.string.tab_title_limit)));

        setTitle(getString(R.string.title_hr_vacation));
        return vpFragmentList;
    }
}
