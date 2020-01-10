package com.dtek.portal.ui.fragment.base;

import androidx.databinding.library.baseAdapters.BR;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.dtek.portal.R;
import com.dtek.portal.databinding.FragmentTabBinding;
import com.dtek.portal.ui.fragment.ViewPagerFragment;
import com.stfalcon.androidmvvmhelper.mvvm.fragments.BindingFragment;

import java.util.List;

public class BaseTabFragment extends BindingFragment<BaseTabFragmentVM, FragmentTabBinding> {

    @Override
    protected BaseTabFragmentVM onCreateViewModel(FragmentTabBinding fragmentTabBinding) {
        BaseTabFragmentVM model = new ViewModelProvider(this).get(BaseTabFragmentVM.class);
        return new BaseTabFragmentVM(this, getViewPagerFragmentList(), getChildFragmentManager());
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


//    protected Context mContext;
//
//    @BindView(R.id.view_pager)
//    protected ViewPager mViewPager;
//    @BindView(R.id.tab_layout)
//    protected TabLayout tabLayout;
//
//    public BaseTabFragment() {
//    }
//
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        mContext = getContext();
//        initFragments();
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View view;
//        view = inflater.inflate(R.layout.fragment_tab_base, container, false);
//        unbinder = ButterKnife.bind(this, view);
//        setupViewPager();
//        return view;
//    }
//
//    protected void initFragments(){}
//
//    protected void setToolbarTitle(String title) {
//        if (getActivity() != null) {
//            ((MainActivity) getActivity()).setTitle(title);
//        }
//    }
//
//    protected void setupViewPager() {
//    }
//
//    protected void initTabLayout() {
//        tabLayout.setupWithViewPager(mViewPager);
//        changeTabTextStyle(0, true);
//        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                changeTabTextStyle(tab.getPosition(), true);
//                refreshItems();
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//                changeTabTextStyle(tab.getPosition(), false);
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//            }
//        });
//    }
//
//    private void changeTabTextStyle(int position, boolean isSelected) {
//        TextView tv = (TextView) (((LinearLayout) ((LinearLayout) tabLayout.getChildAt(0)).getChildAt(position)).getChildAt(1));
//        if (isSelected)
//            tv.setTypeface(tv.getTypeface(), Typeface.BOLD);
//        else {
//            Typeface tf = Typeface.createFromAsset(Objects.requireNonNull(getContext()).getAssets(), "fonts/gotha_pro_reg.otf");
//            tv.setTypeface(tf);
//        }
//    }
//
//    public void refreshItems(){
//    }

}
