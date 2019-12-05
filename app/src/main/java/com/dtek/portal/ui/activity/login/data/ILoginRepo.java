package com.dtek.portal.ui.activity.login.data;

import com.dtek.portal.api.IBase;
import com.dtek.portal.api.IOnErrorListener;
import com.dtek.portal.api.IOnFinishLoadListener;
import com.dtek.portal.models.login.Login;
import com.dtek.portal.models.login.LoginFAQ;

public interface ILoginRepo extends IBase{
    void getLoginFAQ(IOnFinishLoadListener<LoginFAQ> onFinishedListener, IOnErrorListener iBaseOnFinishListener);
    void getAuth(IOnFinishLoadListener<Login> onFinishedListener, IOnErrorListener iBaseOnFinishListener, String login);
    void sign(IOnFinishLoadListener<Login> onFinishedListener, IOnErrorListener iBaseOnFinishListener, String login);
}
