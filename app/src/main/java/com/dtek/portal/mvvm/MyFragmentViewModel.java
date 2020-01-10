package com.dtek.portal.mvvm;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.dtek.portal.api.IOnErrorListener;
import com.stfalcon.androidmvvmhelper.mvvm.fragments.BindingFragment;
import com.stfalcon.androidmvvmhelper.mvvm.fragments.FragmentViewModel;

import static com.dtek.portal.mvvm.MyBindingActivity.ERROR_TOKEN_ACTION;

public class MyFragmentViewModel<F extends MyBindingFragment> extends FragmentViewModel<F> implements IOnErrorListener {

    protected MutableLiveData<String> data;
    private MutableLiveData<Throwable> errorData;
    private MutableLiveData<String> errorServiceData;

    public MyFragmentViewModel(F fragment) {
        super(fragment);
    }

    protected IOnErrorListener getBaseListener(){
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
        updateView();
    }

    @Override
    public void onFailure(Throwable throwable) {
        errorData.postValue(throwable);
        updateView();
    }

    @Override
    public void errorToken() {
        data.postValue(ERROR_TOKEN_ACTION);
        updateView();
    }

    public void updateView(){}

}
