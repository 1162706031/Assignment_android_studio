package com.example.sample.mvvm;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewbinding.ViewBinding;

import java.lang.reflect.ParameterizedType;

public abstract class BaseActivity<VM extends ViewModel, VB extends ViewBinding> extends AppCompatActivity {
    protected VM mViewModel;
    protected VB mViewBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewBinding = getViewBinding();
        setContentView(mViewBinding.getRoot());
        Class<VM> clazz = (Class<VM>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        mViewModel = new ViewModelProvider(this).get(clazz);
        initView();
        initData();
    }

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 初始化界面
     */
    protected abstract void initView();

    /**
     * 设置layout资源id
     */
    protected abstract VB getViewBinding();
}
