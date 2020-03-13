package com.dtek.portal.ui.fragment.hr_vocation;

import androidx.databinding.Bindable;

import com.dtek.portal.ui.adapter.hr_vocation.HrVocationLimitAdapter;

public class HrVocationLimitFragmentVM  {

    private HrVocationLimitAdapter hrVocationLimitAdapter;

    public HrVocationLimitFragmentVM() {
        hrVocationLimitAdapter = new HrVocationLimitAdapter();

    }

//    @Bindable
    public HrVocationLimitAdapter getHrVocationLimitAdapter() {
        return hrVocationLimitAdapter;
    }
}
