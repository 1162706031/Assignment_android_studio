package com.example.sample.logic.classfile;

import static com.example.sample.Constant.FILE_DATA;

import com.example.sample.data.CourseFileBean;
import com.example.sample.databinding.ActivityRenderBinding;
import com.example.sample.mvvm.BaseActivity;
import com.example.sample.viewmodel.RenderViewmodel;
import com.google.gson.Gson;

public class RenderActivity extends BaseActivity<RenderViewmodel, ActivityRenderBinding> {
    @Override
    protected void initData() {
        String filejson = getIntent().getStringExtra(FILE_DATA);
        CourseFileBean data = new Gson().fromJson(filejson, CourseFileBean.class);
        mViewBinding.vvPlay.setVideoPath(data.getFileUrl());
        mViewBinding.vvPlay.start();
    }

    @Override
    protected void initView() {
        String filejson = getIntent().getStringExtra(FILE_DATA);
        CourseFileBean data = new Gson().fromJson(filejson, CourseFileBean.class);

//        NavHostFragment navHosFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.id_fragment_container);
//        NavController navController = navHosFragment.getNavController();
//        NavGraph graph = navController.getGraph();
//        NavArgument argument = new NavArgument.Builder()
//                .setDefaultValue(filejson)
//                .build();
//        graph.addArgument("videoCourseId", argument);
    }

    @Override
    protected ActivityRenderBinding getViewBinding() {
        return ActivityRenderBinding.inflate(getLayoutInflater());
    }
}
