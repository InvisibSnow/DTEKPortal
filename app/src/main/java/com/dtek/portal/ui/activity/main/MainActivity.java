package com.dtek.portal.ui.activity.main;

import androidx.databinding.library.baseAdapters.BR;

import com.dtek.portal.R;

import com.dtek.portal.databinding.ActivityMainBinding;
import com.stfalcon.androidmvvmhelper.mvvm.activities.BindingActivity;

public class MainActivity extends BindingActivity <ActivityMainBinding, MainActivityVM> {

    private MainActivityVM mainActivityVM;

    @Override
    public MainActivityVM onCreate() {
        mainActivityVM = new MainActivityVM(this);
        setTitle("test Title");
        return mainActivityVM;
    }

    @Override
    public int getVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    public void setTitle(String title){
        mainActivityVM.setTitle(title);
    }

}
