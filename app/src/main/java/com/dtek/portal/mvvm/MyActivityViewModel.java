package com.dtek.portal.mvvm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.dtek.portal.utils.IBaseOnFinishListener;
import com.stfalcon.androidmvvmhelper.mvvm.activities.ActivityViewModel;

import static com.dtek.portal.mvvm.MyBindingActivity.ERROR_TOKEN_ACTION;

public class MyActivityViewModel<A extends AppCompatActivity> extends ActivityViewModel<A> implements IBaseOnFinishListener {

    private MutableLiveData<String> data;
    private MutableLiveData<Throwable> errorData;
    private MutableLiveData<String> errorServiceData;

    protected A activity;

    public MyActivityViewModel(A activity) {
        super(activity);
    }

    protected IBaseOnFinishListener getBaseListener(){
        return this;
    }

    public LiveData<String> getData() {
        data = new MutableLiveData<>();
        return data;
    }

    public LiveData<Throwable> getErrorData() {
        errorData = new MutableLiveData<>();
        return errorData;
    }

    public LiveData<String> getErrorServiceData() {
        errorServiceData = new MutableLiveData<>();
        return errorServiceData;
    }

    @Override
    public void onFailure(String error) {
        errorServiceData.postValue(error);
    }

    @Override
    public void onFailure(Throwable throwable) {
        errorData.postValue(throwable);
    }

    @Override
    public void errorToken() {
        data.postValue(ERROR_TOKEN_ACTION);
    }
}
