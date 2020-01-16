package com.dtek.portal.mvvm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dtek.portal.api.IOnErrorListener;

import static com.dtek.portal.mvvm.MyBindingActivity.ERROR_TOKEN_ACTION;
import static com.dtek.portal.mvvm.MyBindingFragment.DISMISS_DIALOG_ACTION;
import static com.dtek.portal.mvvm.MyBindingFragment.SHOW_DIALOG_ACTION;
import static com.dtek.portal.mvvm.MyBindingFragment.UPDATE_VIEW;

public class MyFragmentViewModel<F extends MyBindingFragment>
        extends ViewModel implements IOnErrorListener{

    private F fragment;
    private Activity activity;

    private MutableLiveData<String> data;
    private MutableLiveData<Throwable> errorData;
    private MutableLiveData<String> errorServiceData;

    public MyFragmentViewModel(F fragment) {
        this.fragment = fragment;
        this.activity = this.fragment.getActivity();
    }

    public F getFragment() {
        return fragment;
    }

    public Activity getActivity() {
        return activity;
    }

    /**
     * Fragment lifecycle
     */
    public void onViewCreated() {

    }

    public void onStart() {

    }

    public void onStop() {

    }

    public void onDestroy() {
        //realm.close();
    }

    public void onPause() {

    }

    public void onResume() {

    }

    public void onDestroyView() {
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

    }

    public void onSaveInstanceState(Bundle outState) {
    }

    public void onViewStateRestored(Bundle savedInstanceState) {
    }

    protected IOnErrorListener getOnErrorListener(){
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

    public void dismissWaitDialog(){
        data.postValue(DISMISS_DIALOG_ACTION);
    }

    public void showWaitDialog(){
        data.postValue(SHOW_DIALOG_ACTION);
    }

    public void updateView(){
        data.postValue(UPDATE_VIEW);
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
