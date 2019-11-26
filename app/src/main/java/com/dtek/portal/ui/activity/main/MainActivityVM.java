package com.dtek.portal.ui.activity.main;

import android.view.View;

import androidx.databinding.BindingAdapter;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;

import com.dtek.portal.models.login.ServicePortal;
import com.dtek.portal.models.login.ServicesList;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.stfalcon.androidmvvmhelper.mvvm.activities.ActivityViewModel;

import java.util.ArrayList;
import java.util.List;


public class MainActivityVM extends ActivityViewModel<MainActivity> {

    public final String CAR_SERVICE = "cars";
    public final String ITSM_SERVICE = "OrderItsm";
    public final String AHO_SERVICE = "Aho";
    public final String HR_SERVICE = "Hr";
    public final String QR_SERVICE = "QR";
    public final String BTRIPS_SERVICE = "BTrips";
    public final String REFERENCE_SERVICE = "Forms";

    public final ObservableField<String> title = new ObservableField<>();
    public final ObservableField<ServicesList> servicesList = new ObservableField<>();
    public final ObservableInt bottomSheetBehaviorState = new ObservableInt(BottomSheetBehavior.STATE_EXPANDED);

    public MainActivityVM(MainActivity activity) {
        super(activity);
    }

    void setTitle(String title){
        this.title.set(title);

        List<ServicePortal> servicePortals = new ArrayList<>();      //todo For Test
        servicePortals.add(new ServicePortal(CAR_SERVICE, true));
        servicePortals.add(new ServicePortal(AHO_SERVICE, true));
        servicePortals.add(new ServicePortal(HR_SERVICE, false));
        servicePortals.add(new ServicePortal(BTRIPS_SERVICE, true));

        ServicesList servicesList = new ServicesList();
        servicesList.setServices(servicePortals);
        this.servicesList.set(servicesList);
    }

    @BindingAdapter("bottomSheetBehaviorState")
    public static void setState(View v, int bottomSheetBehaviorState) {
        BottomSheetBehavior<View> viewBottomSheetBehavior = BottomSheetBehavior.from(v);
        viewBottomSheetBehavior.setState(bottomSheetBehaviorState);
    }
}
