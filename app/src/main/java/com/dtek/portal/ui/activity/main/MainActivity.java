package com.dtek.portal.ui.activity.main;

import androidx.databinding.library.baseAdapters.BR;

import com.dtek.portal.R;
import com.dtek.portal.databinding.ActivityMainBinding;
import com.dtek.portal.mvvm.MyBindingActivity;

public class MainActivity extends MyBindingActivity<ActivityMainBinding, MainActivityVM> {

    @Override
    protected MainActivityVM getVM() {
        viewModel = new MainActivityVM(this);
        return viewModel;
    }

    @Override
    public void setTitle(){
        viewModel.setTitle(getString(R.string.title_news));
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
