package com.dtek.portal.ui.activity.main;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.OnLifecycleEvent;

import com.dtek.portal.R;
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
    public final ObservableBoolean isNewsChecked = new ObservableBoolean();
    public final ObservableBoolean isMediaChecked = new ObservableBoolean();
    public final ObservableBoolean isServicesChecked = new ObservableBoolean();

    public final ObservableBoolean firstTime = new ObservableBoolean(true);

    public void setAction(){
        firstTime.set(false);
    }

    public MainActivityVM() {
        iServiceListRepo = new ServiceListRepo();
        constServices = new ConstServices();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume() {
        getServiceList();
        initCurrentFragment();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        iServiceListRepo.cancelAllRequest();
    }

    private void initCurrentFragment() {
        if (serviceData.getValue() == null) {
//            serviceSelected(ConstServices.NEWS_ID); //todo return after testing
            serviceSelected(ConstServices.HR_SERVICE_ID);

            isNewsChecked.set(true);
        }
    }

    @Override
    public void onFinishedLoad(ServiceList data) {
        if (data.getServices() == null) {
            errorToken();
        } else {
            this.serviceList.set(data);
        }
    }

    public void onClickedNews() {
        serviceSelected(ConstServices.NEWS_ID);
        disableAllCheckedBg();
        isNewsChecked.set(true);
        hideAllNavigation();
    }

    public void onClickedMedia() {
        serviceSelected(ConstServices.MEDIA_ID);
        disableAllCheckedBg();
        isMediaChecked.set(true);
        hideAllNavigation();
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
            disableAllCheckedBg();
            isServicesChecked.set(true);
            showHideBSB(serviceListBottomSheetBehaviorState);
        } else {
            errorToken();
        }
    }

    public void mBackGroundClicked() {
        hideAllNavigation();
        mBackground.set(false);
    }

    private void hideAllNavigation() {
        servicesNavigationHide();
        addServicesNavigationHide();
    }

    public void serviceSelected(int serviceID) {
        Log.d("MyLOG", "PostValue ");
        if (serviceData.getValue() != null) {
            if (serviceData.getValue() != serviceID) {
                Log.d("MyLOG", "serviceData.postValue ");
                serviceData.postValue(serviceID);
            }
        } else {
            Log.d("MyLOG", "serviceData.postValue: " + serviceID);
            serviceData.postValue(serviceID);
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
            setCurrentChecked();
        }
    }

    public boolean isLogin() {
        return !PreferenceUtils.getToken().equals("");
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

    @BindingAdapter("myColorAttr")
    public static void bindColorAttr(View view, boolean value) {
        if (PreferenceUtils.getToken().equals("")) {
            view.setBackgroundColor(view.getContext().getResources().getColor(R.color.color_grey_bg));
        } else if (value) {
            view.setBackgroundColor(view.getContext().getResources().getColor(R.color.color_current_choose_item));
        } else {
            view.setBackgroundColor(view.getContext().getResources().getColor(R.color.color_light_blue_bg));
        }
    }

    private void disableAllCheckedBg() {
        isNewsChecked.set(false);
        isMediaChecked.set(false);
        isServicesChecked.set(false);
    }

    private void setCurrentChecked() {
        disableAllCheckedBg();
        if (serviceData.getValue() != null) {
            if (serviceData.getValue() == ConstServices.NEWS_ID) {
                isNewsChecked.set(true);
            } else if (serviceData.getValue() == ConstServices.MEDIA_ID) {
                isMediaChecked.set(true);
            } else {
                isServicesChecked.set(true);
            }
        }
    }

    void setTitle(String title) {
        this.title.set(title);
    }

    private void getServiceList() {
        if (serviceList.get() == null && !PreferenceUtils.getToken().isEmpty()) {
            Log.d("MyLOG", "serviceList.get()");
            iServiceListRepo.getServiceList(this, getOnErrorListener());
        }
    }

    public LiveData<Integer> getServiceData() {
        if (serviceData == null) {
            return serviceData = new MutableLiveData<>();
        } else {
            return serviceData;
        }
    }
}
