package com.example.sample.logic.homepage.fragment;

import android.view.View;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sample.R;
import com.example.sample.data.ClassMates;
import com.example.sample.logic.homepage.adapter.ClassMatesAdapter;
import com.example.sample.mvvm.BaseFragment;
import com.example.sample.mvvm.ItemClickCallBack;
import com.example.sample.viewmodel.HomeViewModel;

import java.util.List;

public class ClassMateFragment extends BaseFragment<HomeViewModel> {

    RecyclerView mRcvList;
    ClassMatesAdapter mListAdapter;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_classmates;
    }

    @Override
    protected void initView(View root) {
        mRcvList = root.findViewById(R.id.rcv_list);

        mListAdapter = new ClassMatesAdapter(new ItemClickCallBack<ClassMates>() {
            @Override
            public void onItemClick(int position, ClassMates data) {
                mViewModel.startSession(data);
            }
        });
        mRcvList.setLayoutManager(new LinearLayoutManager(getContext()));
        mRcvList.setAdapter(mListAdapter);


    }

    @Override
    protected void initData() {
        mViewModel.classMates.observe(this, new Observer<List<ClassMates>>() {
            @Override
            public void onChanged(List<ClassMates> classMates) {
                mListAdapter.refreshData(classMates);
            }
        });
        mViewModel.getClassMate();
    }
}
