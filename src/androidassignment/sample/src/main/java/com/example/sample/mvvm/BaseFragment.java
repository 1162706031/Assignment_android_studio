package com.example.sample.mvvm;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewbinding.ViewBinding;

import java.lang.reflect.ParameterizedType;

public abstract class BaseFragment<VM extends ViewModel, VB extends ViewBinding> extends Fragment {
    protected VM mViewModel;
    protected VB mViewBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Class<VM> clazz = (Class<VM>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        mViewModel = new ViewModelProvider(this).get(clazz);
        mViewBinding = getViewBinding(inflater, container);
        View root = mViewBinding.getRoot();
        initView(root);
        return root;
    }

    protected abstract VB getViewBinding(LayoutInflater inflater, ViewGroup container);


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
    }

    protected abstract void initView(View root);

    protected abstract void initData();
}
