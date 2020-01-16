package com.dtek.portal.ui.fragment.base;

import androidx.databinding.library.baseAdapters.BR;
import androidx.lifecycle.ViewModelProvider;
import com.dtek.portal.R;
import com.dtek.portal.databinding.FragmentTabBinding;
import com.dtek.portal.mvvm.MyBindingFragment;
import com.dtek.portal.ui.fragment.ViewPagerFragment;

import java.util.List;

public class BaseTabFragment extends MyBindingFragment<BaseTabFragmentVM, FragmentTabBinding> {

    @Override
    protected BaseTabFragmentVM onCreateViewModel(FragmentTabBinding fragmentTabBinding) {
       return new ViewModelProvider(this,
               new BaseTabModelFactory(this, getChildFragmentManager(), getViewPagerFragmentList()))
               .get(BaseTabFragmentVM.class);
    }

    protected List<ViewPagerFragment> getViewPagerFragmentList(){
        return null;
    }

    @Override
    public int getVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_tab;
    }
}
