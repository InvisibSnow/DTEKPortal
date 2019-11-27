package com.dtek.portal.ui.activity.main.data;

import androidx.annotation.NonNull;

import com.dtek.portal.api.RestManager;
import com.dtek.portal.models.login.ServiceList;
import com.dtek.portal.utils.IBaseOnFinishListener;
import com.dtek.portal.utils.PreferenceUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


import static com.dtek.portal.utils.Const.HTTP.API_AUTH_ACCESS;


public class ServiceListRepo implements IServiceListRepo {

    private Call serviceListCall;

    @Override
    public void getServiceList(OnFinishedListener onFinishedListener, IBaseOnFinishListener iBaseOnFinishListener) {

        serviceListCall = RestManager.getApi()
                .getServices(API_AUTH_ACCESS + PreferenceUtils.getToken());
        serviceListCall.enqueue(new Callback<ServiceList>() {
            @Override
            public void onResponse(@NonNull Call<ServiceList> call, @NonNull Response<ServiceList> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ServiceList serviceList = response.body();
                    onFinishedListener.onFinishedServiceListLoad(serviceList);
                } else if (response.code() == 401) {
                    iBaseOnFinishListener.errorToken();
                } else {
                    iBaseOnFinishListener.onFailure(response.message());
                }
            }

            @Override
            public void onFailure(@NonNull Call<ServiceList> call, @NonNull Throwable t) {
                iBaseOnFinishListener.onFailure(t);
            }
        });
    }

    @Override
    public void cancelAllRequest() {
        if(serviceListCall!=null)
            serviceListCall.cancel();
    }
}
