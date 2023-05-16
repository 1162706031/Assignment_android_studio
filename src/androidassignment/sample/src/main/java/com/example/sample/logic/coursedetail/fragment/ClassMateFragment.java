package com.example.sample.logic.coursedetail.fragment;

import static com.example.sample.Constant.COURSE_ID;
import static com.example.sample.Constant.SESSION_ID;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.sample.data.ClassMates;
import com.example.sample.databinding.FragmentClassmatesBinding;
import com.example.sample.logic.p2pchat.SessionActivity;
import com.example.sample.mvvm.BaseFragment;
import com.example.sample.mvvm.ItemClickCallBack;
import com.example.sample.viewmodel.CourseClassmatesViewModel;

import java.util.List;

public class ClassMateFragment extends BaseFragment<CourseClassmatesViewModel, FragmentClassmatesBinding> {

    ClassMatesAdapter mListAdapter;

    @Override
    protected FragmentClassmatesBinding getViewBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentClassmatesBinding.inflate(inflater, container, false);
    }

    @Override
    protected void initView(View root) {

        mListAdapter = new ClassMatesAdapter(new ItemClickCallBack<ClassMates>() {
            @Override
            public void onItemClick(int position, ClassMates data) {
                mViewModel.startSession(data);
            }
        });
        mViewBinding.rcvList.setLayoutManager(new LinearLayoutManager(getContext()));
        mViewBinding.rcvList.setAdapter(mListAdapter);


    }

    @Override
    protected void initData() {
        mViewModel.classMatesLiveData.observe(this, new Observer<List<ClassMates>>() {
            @Override
            public void onChanged(List<ClassMates> classMates) {
                mListAdapter.refreshData(classMates);
            }
        });
        mViewModel.jumpToSessionAty.observe(this, new Observer<ClassMates>() {
            @Override
            public void onChanged(ClassMates classMates) {
                Intent intent = new Intent(getActivity(), SessionActivity.class);
                intent.putExtra(SESSION_ID, classMates.getEmail());
                startActivity(intent);
            }
        });
        mViewModel.getCourseSubscribers(getArguments().getString(COURSE_ID));
    }
}
