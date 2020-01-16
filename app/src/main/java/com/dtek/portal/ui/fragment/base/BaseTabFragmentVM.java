package com.dtek.portal.ui.fragment.base;

import android.graphics.Typeface;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.dtek.portal.mvvm.MyFragmentViewModel;
import com.dtek.portal.ui.adapter.BasePagerAdapter;
import com.dtek.portal.ui.fragment.ViewPagerFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.List;
import java.util.Objects;


public class BaseTabFragmentVM extends MyFragmentViewModel {

    private List <ViewPagerFragment> fragmentList;
    private FragmentManager fm;

    @SuppressWarnings("unchecked")
    BaseTabFragmentVM(BaseTabFragment fragment, FragmentManager fragmentManager, List<ViewPagerFragment> fragmentList) {
        super(fragment);
        this.fragmentList = fragmentList;
        this.fm = fragmentManager;
    }

    @BindingAdapter("viewPager")
    public static void setViewPager(ViewPager viewPager, final BaseTabFragmentVM viewModel ){
        BasePagerAdapter pagerAdapter = new BasePagerAdapter(viewModel.fm);
        for(ViewPagerFragment vpFragment: viewModel.fragmentList){
            pagerAdapter.addFragment(vpFragment.getFragment(), vpFragment.getTitle());
        }
        viewPager.setAdapter(pagerAdapter);
    }

    @BindingAdapter({"setUpWithViewpager"})
    public static void bindViewPagerTabs(final TabLayout view, final ViewPager pagerView) {
        view.setupWithViewPager(pagerView, true);
    }

    @BindingAdapter({"setTabListener"})
    public static void bindPagerTabsListener(final TabLayout view, final BaseTabFragmentVM viewModel) {

        view.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                changeTabTextStyle(view, tab.getPosition(), true);
                viewModel.updateView();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                changeTabTextStyle(view, tab.getPosition(), false);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    private static void changeTabTextStyle(TabLayout tabLayout, int position, boolean isSelected) {
        TextView tv = (TextView) (((LinearLayout) ((LinearLayout) tabLayout.getChildAt(0)).getChildAt(position)).getChildAt(1));
        if (isSelected)
            tv.setTypeface(tv.getTypeface(), Typeface.BOLD);
        else {
            Typeface tf = Typeface.createFromAsset(Objects.requireNonNull(tabLayout.getContext()).getAssets(), "fonts/gotha_pro_reg.otf");
            tv.setTypeface(tf);
        }
    }
}
