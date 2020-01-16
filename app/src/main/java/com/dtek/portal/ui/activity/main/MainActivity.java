package com.dtek.portal.ui.activity.main;

import androidx.databinding.library.baseAdapters.BR;
import androidx.lifecycle.LiveData;

import com.dtek.portal.R;
import com.dtek.portal.databinding.ActivityMainBinding;
import com.dtek.portal.mvvm.MyBindingActivity;

import static com.dtek.portal.utils.SwitchFragmentHelper.switchFragment;

public class MainActivity extends MyBindingActivity<ActivityMainBinding, MainActivityVM> {

    @Override
    public MainActivityVM onCreate() {
        return new MainActivityVM(this);
    }

    @Override
    public void initListener() {
        super.initListener();

        LiveData<Integer> data = viewModel.getServiceData();
        data.observe(this, this::changeFragment);
    }

    private void changeFragment(Integer serviceID){
        switchFragment(getSupportFragmentManager(), serviceID);
    }

    @Override
    public int getVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

}
