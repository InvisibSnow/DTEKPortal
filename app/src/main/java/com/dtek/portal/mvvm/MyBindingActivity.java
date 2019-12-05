package com.dtek.portal.mvvm;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LiveData;

import com.dtek.portal.ui.activity.SplashActivity;
import com.dtek.portal.utils.ApiErrors;
import com.stfalcon.androidmvvmhelper.mvvm.activities.BindingActivity;

import java.util.Objects;


@SuppressLint("Registered")
public class MyBindingActivity<B extends ViewDataBinding, VM extends MyActivityViewModel> extends BindingActivity<B, VM> {

    public static final String ERROR_TOKEN_ACTION = "error_token";
    public static final String SHOW_KEYBOARD_ACTION = "show_keyboard";
    public static final String HIDE_KEYBOARD_ACTION = "hide_keyboard";

    protected VM viewModel;

    @Override
    public VM onCreate() {
        viewModel = getVM();
        initListener();

        return viewModel;
    }

    public void initListener() {
        LiveData<String> data = viewModel.getData();
        data.observe(this, this::action);

        LiveData<Throwable> errorData = viewModel.getErrorData();
        errorData.observe(this, this::errorAction);

        LiveData<String> errorServiceData = viewModel.getErrorServiceData();
        errorServiceData.observe(this, this::errorAction);
    }

    private void action(String action) {
        switch (action) {
            case ERROR_TOKEN_ACTION:
                errorToken();
                break;
            case SHOW_KEYBOARD_ACTION:
                showKeyBoard();
                break;
            case HIDE_KEYBOARD_ACTION:
                hideKeyBoard();
                break;
        }
    }

    public void errorToken() {
        Intent intent = new Intent(this, SplashActivity.class);
        startActivity(intent);
        //todo create Exit
    }

    private void errorAction(Throwable throwable) {
        ApiErrors.showError(throwable, getSupportFragmentManager());
    }

    private void errorAction(String error) {
        ApiErrors.showError(error, getSupportFragmentManager());
    }

    protected void setTitle(String title) {
    }

    protected VM getVM() {
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

    public void showKeyBoard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        Objects.requireNonNull(imm).toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }

    public void hideKeyBoard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(Objects.requireNonNull(this.getCurrentFocus()).getWindowToken(), 0);
        }
    }

}
