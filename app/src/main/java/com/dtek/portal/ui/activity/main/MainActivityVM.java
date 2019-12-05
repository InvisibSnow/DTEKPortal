package com.dtek.portal.ui.activity.main;

import android.view.View;
import androidx.databinding.BindingAdapter;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.dtek.portal.models.login.ServiceList;
import com.dtek.portal.mvvm.MyActivityViewModel;
import com.dtek.portal.ui.activity.main.data.IServiceListRepo;
import com.dtek.portal.ui.activity.main.data.ServiceListRepo;
import com.dtek.portal.utils.ConstServices;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

public class MainActivityVM extends MyActivityViewModel<MainActivity> implements IServiceListRepo.OnFinishedListener {

    public ConstServices constServices;

    private IServiceListRepo iServiceListRepo;

    private MutableLiveData<Integer> serviceData;

    public final ObservableField<String> title = new ObservableField<>();
    public final ObservableField<ServiceList> serviceList = new ObservableField<>();
    public final ObservableInt serviceListBottomSheetBehaviorState = new ObservableInt(BottomSheetBehavior.STATE_EXPANDED);
    public final ObservableInt addServiceListBottomSheetBehaviorState = new ObservableInt(BottomSheetBehavior.STATE_EXPANDED);

    public MainActivityVM(MainActivity activity) {
        super(activity);
        iServiceListRepo = new ServiceListRepo();
        constServices = new ConstServices();
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

    public void serviceSelected(int serviceID){
        serviceData.postValue(serviceID);
    }

    public LiveData<Integer> getServiceData() {
        serviceData = new MutableLiveData<>();
        return serviceData;
    }
}
