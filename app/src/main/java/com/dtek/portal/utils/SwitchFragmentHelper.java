package com.dtek.portal.utils;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.dtek.portal.R;
import com.dtek.portal.ui.fragment.news.TabNewsFragment;

public class SwitchFragmentHelper {

    public static void switchFragment(FragmentManager supportFragmentManager, Integer serviceID) {
        Fragment fragment = getFragment(serviceID);
        supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE); //todo обнуляем стєк фрагментов
        if (fragment != null) {
            supportFragmentManager
                    .beginTransaction()
                    .addToBackStack(null)// добавляем фрагменты в BackStack
                    .replace(R.id.fragment_container, fragment)
                    .commitAllowingStateLoss();
        }
    }

    private static Fragment getFragment(Integer serviceID){
        switch (serviceID){
            case ConstServices.CAR_SERVICE_ID:
            case ConstServices.AHO_SERVICE_ID:
            case ConstServices.HR_SERVICE_ID:
            case ConstServices.QR_SERVICE_ID:
            case ConstServices.BTRIPS_SERVICE_ID:
            case ConstServices.REFERENCE_SERVICE_ID:
                return new TabNewsFragment();
        }
        return null;
    }

}
