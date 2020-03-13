package com.dtek.portal.mvvm;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;

import com.dtek.portal.ui.activity.main.MainActivity;
import com.dtek.portal.utils.ApiErrors;

public abstract class MyBindingFragment<VM extends MyFragmentViewModel, B extends ViewDataBinding> extends Fragment {

    public static final String ERROR_TOKEN_ACTION = "error_token";
    public static final String SHOW_DIALOG_ACTION = "dialog_show";
    public static final String DISMISS_DIALOG_ACTION = "dialog_dismiss";
    public static final String UPDATE_VIEW = "update_view";

    protected abstract VM onCreateViewModel(B binding);

    private B binding;
    protected VM viewModel;

    private Bundle savedInstanceState;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.savedInstanceState = savedInstanceState;
        viewModel = onCreateViewModel(binding);
        binding.setVariable(getVariable(), viewModel);
        binding.executePendingBindings();
        getLifecycle().addObserver(viewModel);
        initListener();
    }

    public B getBinding() {
        return binding;
    }

    public VM getViewModel() {
        return viewModel;
    }

    public Bundle getSavedInstanceState() {
        return savedInstanceState;
    }

    public void resetViewModel() {
        viewModel = onCreateViewModel(binding);
        getBinding().setVariable(getVariable(), viewModel);
    }

    public void initListener(){
        LiveData<String> data = viewModel.getData();
        data.observe(this, this::action);

        LiveData<Throwable> errorData = viewModel.getErrorData();
        errorData.observe(this, this::errorAction);

        LiveData<String> errorServiceData = viewModel.getErrorServiceData();
        errorServiceData.observe(this, this::errorAction);
    }

    private void action(String action){
        switch (action){
            case ERROR_TOKEN_ACTION:
                errorToken();
                break;
            case SHOW_DIALOG_ACTION:
                showWaitDialog();
                break;
            case DISMISS_DIALOG_ACTION:
                dismissWaitDialog();
                break;
            case UPDATE_VIEW:
                updateView();
        }
    }

    private void errorAction(Throwable throwable){
        dismissWaitDialog();
        ApiErrors.showError(throwable, getChildFragmentManager());
    }

    private void errorAction(String error){
        dismissWaitDialog();
        ApiErrors.showError(error, getChildFragmentManager());
    }

    public void errorToken(){
        dismissWaitDialog();
        if(getActivity() != null) {
            ((MainActivity) getActivity()).errorToken();
        }
    }

    public void updateView(){}

    public void showWaitDialog(){}

    public void dismissWaitDialog(){}

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (viewModel != null) {
            viewModel.onSaveInstanceState(outState);
        }
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (viewModel != null) {
            viewModel.onViewStateRestored(savedInstanceState);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (viewModel == null) {
            viewModel = onCreateViewModel(binding);
        }
        viewModel.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        viewModel.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    /**
     * Override for set binding variable
     *
     * @return variable id
     */
    public abstract int getVariable();

    /**
     * Override for set layout resource
     *
     * @return layout resource id
     */
    public abstract int getLayoutId();
}
