package com.dtek.portal.ui.fragment.news;

import androidx.databinding.library.baseAdapters.BR;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.dtek.portal.R;
import com.dtek.portal.databinding.FragmentNewsDtekListBinding;
import com.dtek.portal.mvvm.MyBindingFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsDtekListFragment extends MyBindingFragment<NewsDtekListFragmentVM, FragmentNewsDtekListBinding> {

    @Override
    protected NewsDtekListFragmentVM onCreateViewModel(FragmentNewsDtekListBinding binding) {
        return new ViewModelProvider(this)
                .get(NewsDtekListFragmentVM.class);
    }

    public void refresh(){
        viewModel.refresh();
    }

    @Override
    public int getVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_news_dtek_list;
    }
}
