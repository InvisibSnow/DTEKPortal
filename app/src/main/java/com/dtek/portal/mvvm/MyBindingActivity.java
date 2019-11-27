package com.dtek.portal.mvvm;

import android.annotation.SuppressLint;
import android.content.Intent;

import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LiveData;

import com.dtek.portal.ui.activity.SplashActivity;
import com.dtek.portal.utils.ApiErrors;
import com.stfalcon.androidmvvmhelper.mvvm.activities.BindingActivity;


@SuppressLint("Registered")
public class MyBindingActivity<B extends ViewDataBinding , VM extends MyActivityViewModel> extends BindingActivity<B , VM > {

    public static final String ERROR_TOKEN_ACTION = "error_token";

    protected VM viewModel;

    @Override
    public VM onCreate() {
        viewModel = getVM();
        setTitle();
        initListener();
        return viewModel;
    }

    public void initListener(){
        LiveData<String> data = viewModel.getData();
        data.observe(this, this::action);

        LiveData<Throwable> errorData = viewModel.getErrorData();
        errorData.observe(this, this::errorAction);

        LiveData<String> errorServiceData = viewModel.getErrorServiceData();
        errorServiceData.observe(this, this::errorAction);
    }

    private void action(String action){
        switch (action){
            case ERROR_TOKEN_ACTION:
                errorToken();
                break;
        }
    }

    public void errorToken(){
        Intent intent = new Intent(this, SplashActivity.class);
        startActivity(intent);
        //todo create Exit
    }

    private void errorAction(Throwable throwable){
        ApiErrors.showError(throwable, getSupportFragmentManager());
    }

    private void errorAction(String error){
        ApiErrors.showError(error, getSupportFragmentManager());
    }

    protected void setTitle(){
    }

    protected VM getVM(){
        return null;
    }

    @Override
    public int getVariable() {
        return 0;
    }

    @Override
    public int getLayoutId() {
        return 0;
    }

}
