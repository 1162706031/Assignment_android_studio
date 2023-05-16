package com.example.sample.logic.coursedetail.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sample.databinding.FragmentCoursedetailBinding;
import com.example.sample.mvvm.BaseFragment;
import com.example.sample.viewmodel.CourseDetailViewModel;

public class CourseDetailFragment extends BaseFragment<CourseDetailViewModel, FragmentCoursedetailBinding> {

    @Override
    protected FragmentCoursedetailBinding getViewBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentCoursedetailBinding.inflate(inflater, container, false);
    }

    @Override
    protected void initView(View root) {
    }

    @Override
    protected void initData() {

    }
}
