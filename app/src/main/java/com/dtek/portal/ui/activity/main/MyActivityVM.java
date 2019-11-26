package com.dtek.portal.ui.activity.main;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BaseObservable;

public abstract class MyActivityVM<A extends AppCompatActivity> extends BaseObservable {
    protected A activity;

    public MyActivityVM(A activity) {
        this.activity = activity;
    }

    public A getActivity() {
        return this.activity;
    }

    public void finish() {
        this.activity.finish();
    }

    public boolean onBackKeyPress() {
        return false;
    }

    public void onStart() {
    }

    public void onStop() {
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
    }

    public void onDestroy() {
    }

    public void onPause() {
    }

    public void onResume() {
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

    public void onSaveInstanceState(Bundle outState) {
    }

    public void onCreateOptionsMenu(Menu menu) {
    }

    public void onPrepareOptionsMenu(Menu menu) {
    }

    public void onWindowFocusChanged(boolean hasFocus) {
    }
}
