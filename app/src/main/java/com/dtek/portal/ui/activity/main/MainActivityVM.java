package com.dtek.portal.ui.activity.main;

import android.os.Handler;
import android.view.View;

import androidx.databinding.BindingAdapter;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;

import com.dtek.portal.models.login.ServiceList;
import com.dtek.portal.mvvm.MyActivityViewModel;
import com.dtek.portal.ui.activity.main.data.IServiceListRepo;
import com.dtek.portal.ui.activity.main.data.ServiceListRepo;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.stfalcon.androidmvvmhelper.mvvm.activities.ActivityViewModel;

public class MainActivityVM extends MyActivityViewModel<MainActivity> implements IServiceListRepo.OnFinishedListener {

    public final String CAR_SERVICE = "cars";
    public final String ITSM_SERVICE = "OrderItsm";
    public final String AHO_SERVICE = "Aho";
    public final String HR_SERVICE = "Hr";
    public final String QR_SERVICE = "QR";
    public final String BTRIPS_SERVICE = "BTrips";
    public final String REFERENCE_SERVICE = "Forms";

    private IServiceListRepo iServiceListRepo;

    public final ObservableField<String> title = new ObservableField<>();
    public final ObservableField<ServiceList> serviceList = new ObservableField<>();
    public final ObservableInt bottomSheetBehaviorState = new ObservableInt(BottomSheetBehavior.STATE_EXPANDED);

    public MainActivityVM(MainActivity activity) {
        super(activity);
        iServiceListRepo = new ServiceListRepo();
    }

    void setTitle(String title){
        this.title.set(title);
    }

    private void getServiceList(){
        if(serviceList.get()==null){
            iServiceListRepo.getServiceList(this, getBaseListener());
        }
    }

    @Override
    public void onResume() {
        getServiceList();
    }

    @BindingAdapter("bottomSheetBehaviorState")
    public static void setState(View v, int bottomSheetBehaviorState) {
        BottomSheetBehavior<View> viewBottomSheetBehavior = BottomSheetBehavior.from(v);
        viewBottomSheetBehavior.setState(bottomSheetBehaviorState);
    }

    @Override
    public void onFinishedServiceListLoad(ServiceList serviceList) {
        this.serviceList.set(serviceList);
    }
}
