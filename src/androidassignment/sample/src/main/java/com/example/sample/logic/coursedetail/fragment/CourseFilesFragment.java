package com.example.sample.logic.coursedetail.fragment;

import static com.example.sample.Constant.COURSE_ID;
import static com.example.sample.Constant.FILE_DATA;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.sample.data.CourseFileBean;
import com.example.sample.databinding.FragmentFilesBinding;
import com.example.sample.logic.classfile.RenderActivity;
import com.example.sample.logic.coursedetail.adapter.FileAdapter;
import com.example.sample.mvvm.BaseFragment;
import com.example.sample.mvvm.ItemClickCallBack;
import com.example.sample.viewmodel.CourseFilesViewModel;
import com.google.gson.Gson;

import java.util.List;

public class CourseFilesFragment extends BaseFragment<CourseFilesViewModel, FragmentFilesBinding> {

    FileAdapter mListAdapter;

    @Override
    protected FragmentFilesBinding getViewBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentFilesBinding.inflate(inflater, container, false);
    }

    @Override
    protected void initView(View root) {

        mListAdapter = new FileAdapter(new ItemClickCallBack<CourseFileBean>() {
            @Override
            public void onItemClick(int position, CourseFileBean data) {
                Intent intent = new Intent(getActivity(), RenderActivity.class);
                intent.putExtra(FILE_DATA, new Gson().toJson(data));
                startActivity(intent);
            }
        });
        mViewBinding.rcvList.setLayoutManager(new LinearLayoutManager(getContext()));
        mViewBinding.rcvList.setAdapter(mListAdapter);
    }

    @Override
    protected void initData() {
        mViewModel.filesLiveData.observe(this, new Observer<List<CourseFileBean>>() {
            @Override
            public void onChanged(List<CourseFileBean> courseFileBeans) {
                mListAdapter.refreshData(courseFileBeans);
            }
        });
        mViewModel.getCourseFiles(getArguments().getString(COURSE_ID));
    }
}
