package com.example.sample.logic.homepage.fragment;

import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sample.R;
import com.example.sample.data.CourseBean;
import com.example.sample.logic.homepage.adapter.MyCourseAdapter;
import com.example.sample.logic.homepage.adapter.MyCourseIndexAdapter;
import com.example.sample.mvvm.BaseFragment;
import com.example.sample.mvvm.ItemClickCallBack;
import com.example.sample.viewmodel.MyCourseViewModel;

import java.util.List;

public class MyCourseFragment extends BaseFragment<MyCourseViewModel> {

    RecyclerView mRcvIndex, mRcvList;
    MyCourseAdapter mListAdapter;
    MyCourseIndexAdapter mIndexAdapter;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_mycourse;
    }

    @Override
    protected void initView(View root) {
        mRcvIndex = root.findViewById(R.id.rcv_index);
        mRcvList = root.findViewById(R.id.rcv_list);

        mListAdapter = new MyCourseAdapter(new ItemClickCallBack<CourseBean>() {
            @Override
            public void onItemClick(int position, CourseBean data) {
                Toast.makeText(getContext(), String.format("去%s详情页", data.getCourseName()), Toast.LENGTH_SHORT).show();
            }
        });
        mRcvList.setLayoutManager(new LinearLayoutManager(getContext()));
        mRcvList.setAdapter(mListAdapter);

        mIndexAdapter = new MyCourseIndexAdapter(new ItemClickCallBack<String>() {
            @Override
            public void onItemClick(int position, String data) {
                mViewModel.catalogSelected(data);
            }
        });
        mRcvIndex.setLayoutManager(new LinearLayoutManager(getContext()));
        mRcvIndex.setAdapter(mIndexAdapter);


    }

    @Override
    protected void initData() {
        mViewModel.indexList.observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> strings) {
                mIndexAdapter.refreshData(strings);
            }
        });

        mViewModel.currentCourseList.observe(this, new Observer<List<CourseBean>>() {
            @Override
            public void onChanged(List<CourseBean> courseBeans) {
                mListAdapter.refreshData(courseBeans);
            }
        });

        //获取我的课程信息，刷新index
        mViewModel.getAllMyCourseFromDatabase();
    }
}
