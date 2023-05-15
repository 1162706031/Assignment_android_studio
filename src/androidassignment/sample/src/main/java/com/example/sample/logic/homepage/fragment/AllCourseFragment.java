package com.example.sample.logic.homepage.fragment;

import static com.example.sample.Constant.COURSE_ID;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sample.R;
import com.example.sample.data.CourseBean;
import com.example.sample.logic.coursedetail.CourseDetailActivity;
import com.example.sample.logic.homepage.adapter.AllCourseAdapter;
import com.example.sample.mvvm.BaseFragment;
import com.example.sample.mvvm.ItemClickCallBack;
import com.example.sample.viewmodel.AllCourseViewModel;

import java.util.List;

public class AllCourseFragment extends BaseFragment<AllCourseViewModel> {

    RecyclerView mRcv;
    AllCourseAdapter mAdapter;

    EditText mEtSearch;
    ImageButton mBtnSearch;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_allcourse;
    }

    @Override
    protected void initView(View root) {
        mRcv = root.findViewById(R.id.rcv_list);
        mEtSearch = root.findViewById(R.id.et_search);
        mBtnSearch = root.findViewById(R.id.btn_search);
        mAdapter = new AllCourseAdapter(new ItemClickCallBack<CourseBean>() {
            @Override
            public void onItemClick(int position, CourseBean data) {
                Intent intent = new Intent(getContext(), CourseDetailActivity.class);
                intent.putExtra(COURSE_ID, data.getCourseId());
                startActivity(intent);
            }
        });
        mRcv.setLayoutManager(new GridLayoutManager(getContext(), 2));
        mRcv.setAdapter(mAdapter);


        mBtnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content = mEtSearch.getText().toString().trim();

                //search the database
                mViewModel.searchAllCourse(content);
            }
        });

    }

    @Override
    protected void initData() {
        mViewModel.allCourseResult.observe(this, new Observer<List<CourseBean>>() {
            @Override
            public void onChanged(List<CourseBean> courseBeans) {
                mAdapter.refreshData(courseBeans);
            }
        });


        //startup with all data
        mViewModel.searchAllCourse("");
    }
}
