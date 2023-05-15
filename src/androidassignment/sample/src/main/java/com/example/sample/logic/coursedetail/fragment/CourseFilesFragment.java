package com.example.sample.logic.coursedetail.fragment;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sample.R;
import com.example.sample.data.CourseFileBean;
import com.example.sample.logic.coursedetail.adapter.FileAdapter;
import com.example.sample.mvvm.BaseFragment;
import com.example.sample.mvvm.ItemClickCallBack;
import com.example.sample.viewmodel.CourseDetailViewModel;

public class CourseFilesFragment extends BaseFragment<CourseDetailViewModel> {

    RecyclerView mRcvList;
    FileAdapter mListAdapter;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_files;
    }

    @Override
    protected void initView(View root) {
        mRcvList = root.findViewById(R.id.rcv_list);

        mListAdapter = new FileAdapter(new ItemClickCallBack<CourseFileBean>() {
            @Override
            public void onItemClick(int position, CourseFileBean data) {

            }
        });
        mRcvList.setLayoutManager(new LinearLayoutManager(getContext()));
        mRcvList.setAdapter(mListAdapter);
    }

    @Override
    protected void initData() {

    }
}
