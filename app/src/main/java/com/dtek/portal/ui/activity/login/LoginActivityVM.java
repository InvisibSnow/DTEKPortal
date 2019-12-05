package com.dtek.portal.ui.activity.login;

import android.view.View;
import androidx.databinding.BindingAdapter;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.dtek.portal.api.IOnFinishLoadListener;
import com.dtek.portal.models.login.Login;
import com.dtek.portal.models.login.LoginFAQ;
import com.dtek.portal.mvvm.MyActivityViewModel;
import com.dtek.portal.ui.activity.login.data.ILoginRepo;
import com.dtek.portal.ui.activity.login.data.LoginRepo;
import com.dtek.portal.utils.PreferenceUtils;

import java.util.Objects;

import static com.dtek.portal.mvvm.MyBindingActivity.HIDE_KEYBOARD_ACTION;
import static com.dtek.portal.mvvm.MyBindingActivity.SHOW_KEYBOARD_ACTION;

public class LoginActivityVM extends MyActivityViewModel<LoginActivity>  {

    private InLogin inLogin;
    private InLoginFAQ inLoginFAQ;
    private InSign inSign;

    final static String LOGIN_ACTION = "login_action";
    static final String VALIDATION_LOGIN_ERROR_ACTION = "validation_error";

    private ILoginRepo iLoginRepo;
    private String login = "";
    private String signCode = "";

    public final ObservableBoolean progress = new ObservableBoolean();
    public final ObservableBoolean smsCode = new ObservableBoolean();
    public final ObservableBoolean accessCodeRequestFocus = new ObservableBoolean();
    public final ObservableBoolean help = new ObservableBoolean();
    public final ObservableBoolean signEnable = new ObservableBoolean(true);

    public final ObservableField<LoginFAQ> loginFAQ = new ObservableField<>();

    private MutableLiveData<String> loginData;
    private MutableLiveData<String> dialogData;

    LoginActivityVM(LoginActivity activity) {
        super(activity);
        iLoginRepo = new LoginRepo();
        inLogin = new InLogin();
        inLoginFAQ = new InLoginFAQ();
        inSign = new InSign();
    }

    public void onResume() {
        if(loginFAQ.get() == null){
            inLoginFAQ.getLoginFAQ();
        }
    }

    public void showFaq(){
        if(loginFAQ.get() != null) {
            dialogData.setValue(Objects.requireNonNull(loginFAQ.get()).getFaq().toString());
        }
    }

    public void getAuth() {
        inLogin.getAuth();
    }

    public void signIn() {
        signEnable.set(false);
        inSign.sign();
    }

    public void logInWithoutRegistration() {
        loginData.setValue(LOGIN_ACTION);
    }

    private void showKeyboard() {
        accessCodeRequestFocus.set(true);
        data.setValue(SHOW_KEYBOARD_ACTION);
    }

    private void hideKeyboard(){
        accessCodeRequestFocus.set(false);
        data.setValue(HIDE_KEYBOARD_ACTION);
    }

    @Override
    public void updateView() {
        signEnable.set(true);
        progress.set(false);
    }

    @BindingAdapter("requestFocus")
    public static void requestFocus(View view, boolean requestFocus) {
        if (requestFocus) {
            view.requestFocus();
        }
    }

    public LiveData<String> getLoginData() {
        loginData = new MutableLiveData<>();
        return loginData;
    }

    public LiveData<String> getDialogData() {
        dialogData = new MutableLiveData<>();
        return dialogData;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public String getSignCode() {
        return signCode;
    }

    public void setSignCode(String signCode) {
        this.signCode = signCode;
    }

    class InLogin implements IOnFinishLoadListener<Login> {

        void getAuth() {
            if (login.isEmpty() || login.length() <= 3) {
                dialogData.setValue(VALIDATION_LOGIN_ERROR_ACTION);
            } else {
                progress.set(true);
                String strLogin = login.trim().toLowerCase().replaceAll("@dtek.com", "");
                iLoginRepo.getAuth(this, getBaseListener(), strLogin);
                hideKeyboard();
            }
        }

        @Override
        public void onFinishedLoad(Login data) {
            progress.set(false);
            dialogData.setValue(data.getText());
            if (data.isStatus()) {
                smsCode.set(true);
                hideKeyboard();
                showKeyboard();
            }
        }
    }

    class InLoginFAQ implements IOnFinishLoadListener<LoginFAQ> {

        void getLoginFAQ(){
            iLoginRepo.getLoginFAQ(this, getBaseListener());
        }

        @Override
        public void onFinishedLoad(LoginFAQ data) {
            loginFAQ.set(data);
            help.set(true);
        }
    }

    class InSign implements IOnFinishLoadListener<Login> {

        void sign(){
            iLoginRepo.sign(this, getBaseListener(), signCode);
            hideKeyboard();
        }

        @Override
        public void onFinishedLoad(Login login) {
            signEnable.set(true);
            if(login.isStatus()){
                PreferenceUtils.saveToken(login.getText());
                PreferenceUtils.saveUserPhone(login.getTel());
                PreferenceUtils.savePhoneCorporate(login.isCorporate());
                loginData.setValue(LOGIN_ACTION);
            } else {
                dialogData.setValue(login.getText());
            }
        }
    }
}
