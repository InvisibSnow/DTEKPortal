package com.dtek.portal.mvvm;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dtek.portal.api.IOnErrorListener;

import static com.dtek.portal.mvvm.MyBindingActivity.ERROR_TOKEN_ACTION;

public class MyActivityViewModel<A extends AppCompatActivity> extends ViewModel implements IOnErrorListener {

    protected MutableLiveData<String> data;
    private MutableLiveData<Throwable> errorData;
    private MutableLiveData<String> errorServiceData;

    protected A activity;

    public MyActivityViewModel(A activity) {
        this.activity = activity;
    }

    public A getActivity() {
        return activity;
    }

    public void finish() {
        activity.finish();
    }

    /**
     * Activity lifecycle
     */
    public boolean onBackKeyPress() {
        return false;
    }

    public void onStart(){

    }

    public void onStop() {

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }

    public void onDestroy() {
        //realm.close();
    }

    public void onPause() {

    }

    public void onResume(){

    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

    }

    public void onPostCreate(Bundle savedInstanceState) {

    }

    public void onOptionsItemSelected(MenuItem item) {

    }

    public void onConfigurationChanged(Configuration newConfig) {

    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {

    }

    public void onSaveInstanceState(Bundle outState){

    }

    public void onCreateOptionsMenu(Menu menu) {

    }

    public void onPrepareOptionsMenu(Menu menu){

    }

    public void onWindowFocusChanged(boolean hasFocus){

    }

    /**
     * -----------------------
     */

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
