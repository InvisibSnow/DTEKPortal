package com.dtek.portal.ui.fragment.base;

import android.util.Log;

import androidx.databinding.BindingAdapter;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.dtek.portal.ui.adapter.BasePagerAdapter;
import com.dtek.portal.ui.fragment.ViewPagerFragment;
import com.stfalcon.androidmvvmhelper.mvvm.fragments.FragmentViewModel;

import java.util.List;

public class BaseTabFragmentVM extends FragmentViewModel<BaseTabFragment> {

    private List <ViewPagerFragment> fragmentList;
    private FragmentManager fm;

    public BaseTabFragmentVM(BaseTabFragment fragment, List<ViewPagerFragment> fragmentList, FragmentManager fm) {
        super(fragment);
        this.fragmentList = fragmentList;
        this.fm = fm;
    }

    @BindingAdapter("viewPager")
    public static void setViewPager(ViewPager viewPager, final BaseTabFragmentVM viewModel ){
        BasePagerAdapter pagerAdapter = new BasePagerAdapter(viewModel.fm);
        for(ViewPagerFragment vpFragment: viewModel.fragmentList){
            pagerAdapter.addFragment(vpFragment.getFragment(), vpFragment.getTitle());
            Log.d("MyLOG","Add Fragment: " + vpFragment.getFragment().getClass().getName());
        }
        viewPager.setAdapter(pagerAdapter);
    }
}
