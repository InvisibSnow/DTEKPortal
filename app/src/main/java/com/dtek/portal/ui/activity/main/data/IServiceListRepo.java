package com.dtek.portal.ui.activity.main.data;

import com.dtek.portal.models.login.ServiceList;
import com.dtek.portal.utils.IBase;
import com.dtek.portal.utils.IBaseOnFinishListener;

public interface IServiceListRepo extends IBase {
    interface OnFinishedListener {
        void onFinishedServiceListLoad(ServiceList serviceList);
    }

    void getServiceList(OnFinishedListener onFinishedListener, IBaseOnFinishListener iBaseOnFinishListener);
}
