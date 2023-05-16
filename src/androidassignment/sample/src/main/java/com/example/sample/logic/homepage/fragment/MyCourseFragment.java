package com.example.sample.logic.homepage.fragment;

import static com.example.sample.Constant.COURSE_ID;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.sample.data.CourseBean;
import com.example.sample.databinding.FragmentMycourseBinding;
import com.example.sample.logic.coursedetail.CourseInfoActivity;
import com.example.sample.logic.homepage.adapter.AllCourseAdapter;
import com.example.sample.mvvm.BaseFragment;
import com.example.sample.mvvm.ItemClickCallBack;
import com.example.sample.viewmodel.MyCourseViewModel;

import java.util.List;

public class MyCourseFragment extends BaseFragment<MyCourseViewModel, FragmentMycourseBinding> {

    AllCourseAdapter mAdapter;


    @Override
    protected FragmentMycourseBinding getViewBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentMycourseBinding.inflate(inflater, container, false);
    }

    @Override
    protected void initView(View root) {
        mAdapter = new AllCourseAdapter(new ItemClickCallBack<CourseBean>() {
            @Override
            public void onItemClick(int position, CourseBean data) {
                Intent intent = new Intent(getContext(), CourseInfoActivity.class);
                intent.putExtra(COURSE_ID, data.getCourseId());
                startActivity(intent);
            }
        });
        mViewBinding.rcvList.setLayoutManager(new GridLayoutManager(getContext(), 2));
        mViewBinding.rcvList.setAdapter(mAdapter);


    }

    @Override
    protected void initData() {
        mViewModel.currentCourseList.observe(this, new Observer<List<CourseBean>>() {
            @Override
            public void onChanged(List<CourseBean> courseBeans) {
                mAdapter.refreshData(courseBeans);
            }
        });


        //startup with all data
        mViewModel.searchAllCourse("");

    }
}
