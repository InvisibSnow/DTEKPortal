package com.dtek.portal.ui.activity.login;

import android.content.Intent;

import androidx.databinding.library.baseAdapters.BR;
import androidx.lifecycle.LiveData;

import com.dtek.portal.R;
import com.dtek.portal.databinding.ActivityLoginBinding;
import com.dtek.portal.mvvm.MyBindingActivity;
import com.dtek.portal.ui.activity.main.MainActivity;
import com.dtek.portal.ui.dialog.MyDialog;

import java.util.Objects;

import static com.dtek.portal.ui.activity.login.LoginActivityVM.LOGIN_ACTION;
import static com.dtek.portal.ui.activity.login.LoginActivityVM.VALIDATION_LOGIN_ERROR_ACTION;

public class LoginActivity extends MyBindingActivity<ActivityLoginBinding, LoginActivityVM> {

    @Override
    protected LoginActivityVM getVM() {
        viewModel = new LoginActivityVM(this);
        return viewModel;
    }

    @Override
    public void initListener() {
        super.initListener();

        LiveData<String> loginData = viewModel.getLoginData();
        loginData.observe(this, this::loginAction);

        LiveData<String> dialogData = viewModel.getDialogData();
        dialogData.observe(this, this::dialogAction);
    }

    private void loginAction(String action) {
        if(action.equals(LOGIN_ACTION)){
            startActivity(new Intent(this, MainActivity.class));
        }
    }

    private void dialogAction(String action){
        if (VALIDATION_LOGIN_ERROR_ACTION.equals(action)) {
            new MyDialog(getString(R.string.text_dialog_login)).show(getSupportFragmentManager(), "fragmentDialog");
        } else {
            new MyDialog(action).show(Objects.requireNonNull(getSupportFragmentManager()), "fragmentDialog");
        }
    }

    @Override
    public int getVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }
}
