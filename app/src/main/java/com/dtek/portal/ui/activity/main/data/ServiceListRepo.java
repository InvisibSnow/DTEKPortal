package com.dtek.portal.ui.activity.main.data;

import com.dtek.portal.api.BaseCall;
import com.dtek.portal.api.IOnFinishLoadListener;
import com.dtek.portal.api.RestManager;
import com.dtek.portal.models.login.ServiceList;
import com.dtek.portal.api.IOnErrorListener;
import com.dtek.portal.utils.PreferenceUtils;
import retrofit2.Call;
import static com.dtek.portal.utils.Const.HTTP.API_AUTH_ACCESS;

public class ServiceListRepo implements IServiceListRepo {

    private Call<ServiceList> serviceListCall;

    @Override
    public void getServiceList(IOnFinishLoadListener<ServiceList> onFinishedListener, IOnErrorListener iBaseOnFinishListener) {
        cancelCall(serviceListCall);
        serviceListCall = RestManager.getApi().getServices(API_AUTH_ACCESS + PreferenceUtils.getToken());
        new BaseCall<ServiceList>().retrofitCall(serviceListCall, onFinishedListener, iBaseOnFinishListener);
    }

    @Override
    public void cancelAllRequest() {
       cancelCall(serviceListCall);
    }

    private void cancelCall(Call call){
        if (call != null){
            call.cancel();
        }
    }
}
