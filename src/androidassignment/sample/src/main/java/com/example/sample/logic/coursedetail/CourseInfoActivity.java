package com.example.sample.logic.coursedetail;

import static com.example.sample.Constant.COURSE_ID;

import android.view.View;

import androidx.lifecycle.Observer;
import androidx.viewpager.widget.ViewPager;

import com.example.sample.data.CourseBean;
import com.example.sample.databinding.ActivityCourseInfoBinding;
import com.example.sample.logic.coursedetail.ui.main.SectionsPagerAdapter;
import com.example.sample.mvvm.BaseActivity;
import com.example.sample.utils.image.impls.GlideImageLoader;
import com.example.sample.viewmodel.CourseDetailViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

public class CourseInfoActivity extends BaseActivity<CourseDetailViewModel, ActivityCourseInfoBinding> {


    @Override
    protected void initData() {
        mViewModel.courseLiveData.observe(this, new Observer<CourseBean>() {
            @Override
            public void onChanged(CourseBean courseBean) {
                mViewBinding.tvName.setText(courseBean.getCourseName());
                GlideImageLoader.load(mViewBinding.ivCover, courseBean.getCoverUrl());
            }
        });
        mViewModel.getCourse();
    }

    @Override
    protected void initView() {
        String courseId = getIntent().getStringExtra(COURSE_ID);

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager(), courseId);
        ViewPager viewPager = mViewBinding.viewPager;
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = mViewBinding.tabs;
        tabs.setupWithViewPager(viewPager);
        FloatingActionButton fab = mViewBinding.fab;

        //检查是否已订阅
        mViewModel.checkSubscribe(courseId);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewModel.subscribe();
                mViewBinding.fab.setVisibility(View.GONE);
            }
        });

    }

    @Override
    protected ActivityCourseInfoBinding getViewBinding() {
        return ActivityCourseInfoBinding.inflate(getLayoutInflater());
    }
}