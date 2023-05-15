package com.example.sample.logic.coursedetail;

import static com.example.sample.Constant.COURSE_ID;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.lifecycle.Observer;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.sample.R;
import com.example.sample.data.CourseBean;
import com.example.sample.mvvm.BaseActivity;
import com.example.sample.utils.image.impls.GlideImageLoader;
import com.example.sample.viewmodel.CourseDetailViewModel;

public class CourseDetailActivity extends BaseActivity<CourseDetailViewModel> {
    TextView mTvDetail, mTvFiles, mTvClassMates, mTvRecord;
    TextView mTvCourseName, mTvTeacher, mTvCatalog;
    ImageView mIvCover;
    View mBtnSubscribe;

    @Override
    protected void initData() {
        mViewModel.courseLiveData.observe(this, new Observer<CourseBean>() {
            @Override
            public void onChanged(CourseBean courseBean) {
                mTvCatalog.setText(courseBean.getCatalog());
                mTvTeacher.setText(courseBean.getTeacherName());
                mTvCourseName.setText(courseBean.getCourseName());
                GlideImageLoader.load(mIvCover, courseBean.getCoverUrl());
            }
        });
        mViewModel.getCourse(getIntent().getStringExtra(COURSE_ID));

    }

    NavController navController;

    @Override
    protected void initView() {
        mTvDetail = findViewById(R.id.tv_detail);
        mTvFiles = findViewById(R.id.tv_files);
        mTvClassMates = findViewById(R.id.tv_classmates);
        mTvRecord = findViewById(R.id.tv_record);
        mTvCourseName = findViewById(R.id.tv_coursename);
        mIvCover = findViewById(R.id.iv_cover);
        mTvTeacher = findViewById(R.id.tv_teacher);
        mTvCatalog = findViewById(R.id.tv_catalog);
        mBtnSubscribe = findViewById(R.id.tv_sub);
        NavHostFragment navHosFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.id_fragment_container);
        navController = navHosFragment.getNavController();
        mTvDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.Fragment_Page_1, getIntent().getExtras());
            }
        });
        mTvFiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.Fragment_Page_2, getIntent().getExtras());
            }
        });
        mTvClassMates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.Fragment_Page_3, getIntent().getExtras());
            }
        });
        mTvRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.Fragment_Page_4, getIntent().getExtras());
            }
        });
        mBtnSubscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.subscribe(getIntent().getStringExtra(COURSE_ID));
            }
        });

    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_course_detail;
    }
}
