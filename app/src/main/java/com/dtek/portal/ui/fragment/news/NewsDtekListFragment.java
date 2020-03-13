package com.dtek.portal.ui.fragment.news;

import androidx.databinding.library.baseAdapters.BR;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.dtek.portal.R;
import com.dtek.portal.databinding.FragmentNewsDtekListBinding;
import com.dtek.portal.mvvm.MyBindingFragment;
import com.dtek.portal.ui.adapter.news.NewsListAdapter;

import static com.dtek.portal.utils.Const.News.NEWS_CAT_DTEK;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsDtekListFragment extends MyBindingFragment<NewsDtekListFragmentVM, FragmentNewsDtekListBinding> {

    private NewsDtekListFragmentVM myViewModel;

    @Override
    protected NewsDtekListFragmentVM onCreateViewModel(FragmentNewsDtekListBinding binding) {
        myViewModel = new ViewModelProvider(this, new NewsDtekListModelFactory(this, getNewsListAdapter(), getNewsCategory()))
                .get(NewsDtekListFragmentVM.class);
        myViewModel.refresh();
        return myViewModel;
    }

    public NewsListAdapter getNewsListAdapter() {
        return new NewsListAdapter();
    }

    public String getNewsCategory(){
        return NEWS_CAT_DTEK;
    }

    public void refresh() {
        if (myViewModel != null) {
            getViewModel().refresh();
        }
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
