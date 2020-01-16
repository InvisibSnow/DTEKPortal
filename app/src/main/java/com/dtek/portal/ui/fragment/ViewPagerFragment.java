package com.dtek.portal.ui.fragment;

import androidx.fragment.app.Fragment;

public class ViewPagerFragment {
    private Fragment fragment;
    private String title;

    public ViewPagerFragment(Fragment fragment, String title) {
        this.fragment = fragment;
        this.title = title;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public String getTitle() {
        return title;
    }

}
