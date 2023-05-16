package com.example.sample.logic.classfile.fragment;

import static com.example.sample.Constant.FILE_DATA;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sample.data.CourseFileBean;
import com.example.sample.databinding.FragmentRendervideoBinding;
import com.example.sample.mvvm.BaseFragment;
import com.example.sample.viewmodel.RenderViewmodel;
import com.google.gson.Gson;

public class VideoRenderFragment extends BaseFragment<RenderViewmodel, FragmentRendervideoBinding> {
    @Override
    protected FragmentRendervideoBinding getViewBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentRendervideoBinding.inflate(inflater, container, false);
    }

    @Override
    protected void initView(View root) {

    }

    @Override
    protected void initData() {
        String filejson = getArguments().getString(FILE_DATA);
        CourseFileBean data = new Gson().fromJson(filejson, CourseFileBean.class);
        mViewBinding.vvPlay.setVideoPath(data.getFileUrl());
        mViewBinding.vvPlay.start();
    }
}
