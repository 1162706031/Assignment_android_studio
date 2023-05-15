package com.example.sample.logic.coursedetail.fragment;

import android.view.View;
import android.widget.TextView;

import com.example.sample.R;
import com.example.sample.mvvm.BaseFragment;
import com.example.sample.viewmodel.CourseDetailViewModel;

public class CourseDetailFragment extends BaseFragment<CourseDetailViewModel> {
    TextView mTvInfo;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_coursedetail;
    }

    @Override
    protected void initView(View root) {
        mTvInfo = root.findViewById(R.id.tv_info);
        mTvInfo.setText("详情");
    }

    @Override
    protected void initData() {

    }
}
