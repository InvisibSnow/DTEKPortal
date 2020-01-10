package com.dtek.portal.mvvm;


import android.content.Context;
import android.content.Intent;
import android.view.inputmethod.InputMethodManager;

import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LiveData;

import com.dtek.portal.ui.activity.login.LoginActivity;
import com.dtek.portal.utils.ApiErrors;
import com.dtek.portal.utils.PreferenceUtils;
import com.stfalcon.androidmvvmhelper.mvvm.fragments.BindingFragment;

import java.util.Objects;

public class MyBindingFragment<VM extends MyFragmentViewModel, B extends ViewDataBinding> extends BindingFragment<VM, B> {

    public static final String ERROR_TOKEN_ACTION = "error_token";
    public static final String SHOW_KEYBOARD_ACTION = "show_keyboard";
    public static final String HIDE_KEYBOARD_ACTION = "hide_keyboard";

    protected VM viewModel;

    @Override
    protected VM onCreateViewModel(B b) {
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
        if(getActivity() != null) {
            PreferenceUtils.saveToken("");
            Intent intent = new Intent(getContext(), LoginActivity.class);
            startActivity(intent);
            getActivity().finish();
        }
    }

    private void errorAction(Throwable throwable) {
        if (getActivity() != null) {
            ApiErrors.showError(throwable, getActivity().getSupportFragmentManager());
        }
    }

    private void errorAction(String error) {
        if (getActivity() != null) {
            ApiErrors.showError(error, getActivity().getSupportFragmentManager());
        }
    }

    public void showKeyBoard() {
        if(getActivity() != null) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            Objects.requireNonNull(imm).toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
        }
    }

    public void hideKeyBoard() {
        if(getActivity() != null) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(Objects.requireNonNull(getActivity().getCurrentFocus()).getWindowToken(), 0);
            }
        }
    }

    @Override
    public int getVariable() {
        return 0;
    }

    @Override
    public int getLayoutId() {
        return 0;
    }

    protected VM getVM() {
        return null;
    }
}