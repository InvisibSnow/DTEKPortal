package com.dtek.portal.ui.fragment;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.dtek.portal.R;
import com.dtek.portal.ui.activity.main.MainActivity;
import com.google.android.material.tabs.TabLayout;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class BaseTabFragment extends Fragment {

    private Unbinder unbinder;
    protected Context mContext;

    @BindView(R.id.view_pager)
    protected ViewPager mViewPager;
    @BindView(R.id.tab_layout)
    protected TabLayout tabLayout;

    public BaseTabFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
        initFragments();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view;
        view = inflater.inflate(R.layout.fragment_tab_base, container, false);
        unbinder = ButterKnife.bind(this, view);
        setupViewPager();
        return view;
    }

    protected void initFragments(){}

    protected void setToolbarTitle(String title) {
        if (getActivity() != null) {
            ((MainActivity) getActivity()).setTitle(title);
        }
    }

    protected void setupViewPager() {
    }

    protected void initTabLayout() {
        tabLayout.setupWithViewPager(mViewPager);
        changeTabTextStyle(0, true);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                changeTabTextStyle(tab.getPosition(), true);
                refreshItems();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                changeTabTextStyle(tab.getPosition(), false);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

//    protected void initGlobalListener() {   //todo For search TabLayout
//        ViewTreeObserver vto = tabLayout.getViewTreeObserver();
//        vto.addOnGlobalLayoutListener(this::setTabMode);
//    }
//
//    protected void setTabMode() {
//        try {
//            if (((tabLayout.getChildAt(0)).getWidth() + ibSearch.getWidth()) <= clTab.getWidth())
//                tabLayout.setTabMode(TabLayout.MODE_FIXED);
//        } catch (NullPointerException ignored) {
//        }
//    }

    private void changeTabTextStyle(int position, boolean isSelected) {
        TextView tv = (TextView) (((LinearLayout) ((LinearLayout) tabLayout.getChildAt(0)).getChildAt(position)).getChildAt(1));
        if (isSelected)
            tv.setTypeface(tv.getTypeface(), Typeface.BOLD);
        else {
            Typeface tf = Typeface.createFromAsset(Objects.requireNonNull(getContext()).getAssets(), "fonts/gotha_pro_reg.otf");
            tv.setTypeface(tf);
        }
    }

//    @OnClick(R.id.ib_search)
//    public void onItemClick() {
//        performSearch();
//    }
//
//    protected void performSearch() {
//    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    public void refreshItems(){
    }

}
