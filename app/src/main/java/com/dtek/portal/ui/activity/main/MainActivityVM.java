package com.dtek.portal.ui.activity.main;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.dtek.portal.api.IOnFinishLoadListener;
import com.dtek.portal.models.login.ServiceList;
import com.dtek.portal.mvvm.MyActivityViewModel;
import com.dtek.portal.ui.activity.main.data.IServiceListRepo;
import com.dtek.portal.ui.activity.main.data.ServiceListRepo;
import com.dtek.portal.utils.ConstServices;
import com.dtek.portal.utils.PreferenceUtils;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

public class MainActivityVM extends MyActivityViewModel<MainActivity> implements IOnFinishLoadListener<ServiceList> {

    public ConstServices constServices;
    private IServiceListRepo iServiceListRepo;
    private MutableLiveData<Integer> serviceData;

    public final ObservableField<String> title = new ObservableField<>();
    public final ObservableField<ServiceList> serviceList = new ObservableField<>();
    public final ObservableInt serviceListBottomSheetBehaviorState = new ObservableInt(BottomSheetBehavior.STATE_HIDDEN);
    public final ObservableInt addServiceListBottomSheetBehaviorState = new ObservableInt(BottomSheetBehavior.STATE_HIDDEN);
    public final ObservableBoolean mBackground = new ObservableBoolean();
    public final ObservableBoolean mBackgroundColor = new ObservableBoolean();

    public MainActivityVM(MainActivity activity) {
        super(activity);
        iServiceListRepo = new ServiceListRepo();
        constServices = new ConstServices();
    }

    void setTitle(String title) {
        this.title.set(title);
    }

    private void getServiceList() {
        if (serviceList.get() == null && !PreferenceUtils.getToken().isEmpty()) {
            iServiceListRepo.getServiceList(this, getBaseListener());
        }
    }

    @Override
    public void onResume() {
        getServiceList();
        initCurrentFragment();
    }

    private void initCurrentFragment() {
        if (serviceData.getValue() == null) {
            serviceSelected(ConstServices.NEWS_ID);
        }
    }

    @BindingAdapter("bottomSheetBehaviorState")
    public static void setState(View v, int bottomSheetBehaviorState) {
        BottomSheetBehavior<View> viewBottomSheetBehavior = BottomSheetBehavior.from(v);
        viewBottomSheetBehavior.setState(bottomSheetBehaviorState);

        viewBottomSheetBehavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (newState == BottomSheetBehavior.STATE_DRAGGING) {
                    viewBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                } else if (newState == BottomSheetBehavior.STATE_HIDDEN) {
//                    bottomSheetAddRequestView.setVisibility(View.GONE); //при вызове клавиатуры в фрагментах вылазит BottomSheetBehavior, как на него повлият хз, так что такой вот костыль
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
            }
        });
    }

    @Override
    public void onFinishedLoad(ServiceList data) {
        if (data.getServices() == null) {
            errorToken();
        } else {
            this.serviceList.set(data);
        }
    }

    public void serviceSelected(int serviceID) {
        if (serviceData.getValue() != null) {
            if (serviceData.getValue() != serviceID) {
                serviceData.postValue(serviceID);
            }
        } else {
            serviceData.postValue(serviceID);
        }
    }

    public LiveData<Integer> getServiceData() {
        serviceData = new MutableLiveData<>();
        return serviceData;
    }

    public void onClickServicesAdd() {
        if (isLogin()) {
            servicesNavigationHide();
            showHideBSB(addServiceListBottomSheetBehaviorState);
        } else {
            errorToken();
        }
    }

    public void onClickServices() {
        if (isLogin()) {
            addServicesNavigationHide();
            showHideBSB(serviceListBottomSheetBehaviorState);
        } else {
            errorToken();
        }
    }

    private void servicesNavigationHide() {
        serviceListBottomSheetBehaviorState.set(BottomSheetBehavior.STATE_HIDDEN);
    }

    private void addServicesNavigationHide() {
        addServiceListBottomSheetBehaviorState.set(BottomSheetBehavior.STATE_HIDDEN);
    }

    private void showHideBSB(ObservableInt observableInt) {
        if (observableInt.get() == BottomSheetBehavior.STATE_HIDDEN) {
            observableInt.set(BottomSheetBehavior.STATE_EXPANDED);
            mBackground.set(true);
        } else {
            observableInt.set(BottomSheetBehavior.STATE_HIDDEN);
            mBackground.set(false);
        }
    }

    public boolean isLogin() {
        return !PreferenceUtils.getToken().equals("");
    }

    public void mBackGroundClicked() {
        servicesNavigationHide();
        addServicesNavigationHide();
        mBackground.set(false);
    }
}
