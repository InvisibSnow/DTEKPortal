package com.dtek.portal.ui.activity.main.data;

import com.dtek.portal.api.IOnFinishLoadListener;
import com.dtek.portal.models.login.ServiceList;
import com.dtek.portal.api.IBase;
import com.dtek.portal.api.IOnErrorListener;

public interface IServiceListRepo extends IBase {
    void getServiceList(IOnFinishLoadListener<ServiceList> onFinishedListener, IOnErrorListener iBaseOnFinishListener);
}
