package com.dtek.portal.ui.activity.login.data;

import com.dtek.portal.api.BaseCall;
import com.dtek.portal.api.IOnErrorListener;
import com.dtek.portal.api.IOnFinishLoadListener;
import com.dtek.portal.api.RestManager;
import com.dtek.portal.models.login.Login;
import com.dtek.portal.models.login.LoginFAQ;
import com.dtek.portal.utils.PreferenceUtils;

import retrofit2.Call;

import static com.dtek.portal.utils.Const.Login.API_AUTH_SMS;
import static com.dtek.portal.utils.Const.Login.API_AUTH_TOKEN;

public class LoginRepo implements ILoginRepo {

    private Call<LoginFAQ> loginFAQCall;
    private Call<Login> smsCodeCall;
    private Call<Login> signCall;

    @Override
    public void getLoginFAQ(IOnFinishLoadListener<LoginFAQ> onFinishedListener, IOnErrorListener iBaseOnFinishListener) {
        cancelCall(loginFAQCall);
        loginFAQCall = RestManager.getApi().getLoginFAQ();
        new BaseCall<LoginFAQ>().retrofitCall(loginFAQCall, onFinishedListener, iBaseOnFinishListener);
    }

    @Override
    public void getAuth(IOnFinishLoadListener<Login> onFinishedListener, IOnErrorListener iBaseOnFinishListener, String login) {
        cancelCall(smsCodeCall);
        smsCodeCall = RestManager.getApi().getAuth(API_AUTH_SMS + login);
        new BaseCall<Login>(login).retrofitCall(smsCodeCall, onFinishedListener, iBaseOnFinishListener);
    }

    @Override
    public void sign(IOnFinishLoadListener<Login> onFinishedListener, IOnErrorListener iBaseOnFinishListener, String signCode) {
        signCall = RestManager.getApi().sign(API_AUTH_TOKEN + PreferenceUtils.getLogin() + "/" + signCode);
        new BaseCall<Login>().retrofitCall(signCall, onFinishedListener, iBaseOnFinishListener);
    }

    @Override
    public void cancelAllRequest() {
        cancelCall(loginFAQCall);
        cancelCall(smsCodeCall);
        cancelCall(signCall);
    }

    private void cancelCall(Call call){
        if(call != null){
            call.cancel();
        }
    }
}
