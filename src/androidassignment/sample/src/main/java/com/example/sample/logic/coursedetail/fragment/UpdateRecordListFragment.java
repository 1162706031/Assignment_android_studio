package com.example.sample.logic.coursedetail.fragment;

import static com.example.sample.Constant.COURSE_ID;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sample.data.CourseRecordBean;
import com.example.sample.databinding.FragmentFilesBinding;
import com.example.sample.logic.coursedetail.adapter.RecordAdapter;
import com.example.sample.mvvm.BaseFragment;
import com.example.sample.viewmodel.CourseRecordViewModel;

import java.util.List;

public class UpdateRecordListFragment extends BaseFragment<CourseRecordViewModel, FragmentFilesBinding> {
    RecyclerView mRcvList;
    RecordAdapter mListAdapter;

    @Override
    protected FragmentFilesBinding getViewBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentFilesBinding.inflate(inflater, container, false);
    }

    @Override
    protected void initView(View root) {

        mListAdapter = new RecordAdapter();
        mViewBinding.rcvList.setLayoutManager(new LinearLayoutManager(getContext()));
        mViewBinding.rcvList.setAdapter(mListAdapter);
    }

    @Override
    protected void initData() {
        mViewModel.recordsLiveData.observe(this, new Observer<List<CourseRecordBean>>() {
            @Override
            public void onChanged(List<CourseRecordBean> courseRecordBeans) {
                mListAdapter.refreshData(courseRecordBeans);
            }
        });
        mViewModel.getCourseRecord(COURSE_ID);

    }
}
