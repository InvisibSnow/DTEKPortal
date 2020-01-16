package com.dtek.portal.ui.fragment.base;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.dtek.portal.ui.fragment.ViewPagerFragment;

import java.util.List;

public class BaseTabModelFactory extends ViewModelProvider.NewInstanceFactory {

    private BaseTabFragment fragment;
    private FragmentManager fragmentManager;
    private List<ViewPagerFragment> viewPagerFragmentList;

    BaseTabModelFactory(BaseTabFragment fragment, FragmentManager fragmentManager, List<ViewPagerFragment> viewPagerFragmentList){
        super();
        this.fragment = fragment;
        this.fragmentManager = fragmentManager;
        this.viewPagerFragmentList = viewPagerFragmentList;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(BaseTabFragmentVM.class)) {
            return (T) new BaseTabFragmentVM(fragment, fragmentManager, viewPagerFragmentList);
        }
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }

}
