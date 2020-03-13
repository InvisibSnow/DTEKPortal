package com.dtek.portal.ui.activity.main;

import android.util.Log;

import androidx.databinding.library.baseAdapters.BR;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import com.dtek.portal.R;
import com.dtek.portal.databinding.ActivityMainBinding;
import com.dtek.portal.mvvm.MyBindingActivity;

import static com.dtek.portal.utils.SwitchFragmentHelper.switchFragment;

public class MainActivity extends MyBindingActivity<ActivityMainBinding, MainActivityVM> {

    @Override
    public MainActivityVM onCreate() {
        return new ViewModelProvider(this).get(MainActivityVM.class);
    }

    @Override
    public void initListener() {
        super.initListener();

        financialTest(); //todo DELETE THERE

        LiveData<Integer> data = viewModel.getServiceData();
        data.observe(this, this::changeFragment);
    }

    private void financialTest(){
        float sum = 0f;
        float sumOfTheYear = 912.5f;
        float sumOfTheDay = 1f;
        for(int i = 1; i < 20; i++){
            sum = sum + sumOfTheYear;
            float percent = (sum * 1.08f) - sum;
            Log.d("MyLOG", "PERCENT OF " + i + " year = " + percent);
            sum = sum + percent;

            for(int j = 1; j < 13; j++) {
//                sum = (sum + sumOfTheYear) * 1.08f;
//                float percent = (sum + sumOfTheDay) * 0.022f;
//                sum = sum + sumOfTheDay + ((sum + sumOfTheDay) * 0.022f);
//                sum = sum + sumOfTheDay ;
//                sum = sum + percent;

//                float percent = (sum + (sumOfTheDay * 30f)) * 1.0066f;
//                Log.d("MyLOG", "PERCENT OG " + j + " month = " + percent);
//                sum = sum + (sumOfTheDay * 30f) + percent ;

//                sum = (sum + (sumOfTheDay * 30f)) * 1.0066f; ;
//                Log.d("MyLOG", "SUM OF " + j + " month = " + sum);
            }
//            sum = currSum;
            Log.d("MyLOG", "SUM OF " + i + " year = " + sum);
        }
    }

    public void changeFragment(Integer serviceID) {
        Log.d("MyLOG", "changeFragment");
        if(viewModel.firstTime.get()) {
            Log.d("MyLOG", "FirstTime");
            viewModel.setAction();
            switchFragment(getSupportFragmentManager(), serviceID);
        }
    }

    @Override
    public void setTitle(CharSequence title) {
        viewModel.setTitle(title.toString());
    }

    public void setTitle(String title){
        viewModel.setTitle(title);
    }

    @Override
    public int getVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

}
