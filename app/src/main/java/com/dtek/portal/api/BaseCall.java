package com.dtek.portal.api;

import androidx.annotation.NonNull;

import com.dtek.portal.utils.PreferenceUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BaseCall<T> {

    private String loginToSave;

    public BaseCall() {
    }

    public BaseCall(String loginToSave) {
        this.loginToSave = loginToSave;
    }

    public void retrofitCall(Call<T> call, IOnFinishLoadListener<T> iOnFinishLoadListener, IOnErrorListener iOnErrorListener) {
        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(@NonNull Call<T> call, @NonNull Response<T> response) {
                if (response.isSuccessful() && response.body() != null) {
                    savePref();
                    iOnFinishLoadListener.onFinishedLoad(response.body());
                } else if (response.code() == 401) {
                    iOnErrorListener.errorToken();
                } else {
                    iOnErrorListener.onFailure(response.message());
                }
            }

            @Override
            public void onFailure(@NonNull Call<T> call, @NonNull Throwable t) {
                if (!call.isCanceled()) {
                    iOnErrorListener.onFailure(t);
                }
            }
        });
    }

    private void savePref(){
        if (loginToSave != null) {
            PreferenceUtils.saveLogin(loginToSave);
        }
    }

}
