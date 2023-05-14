package com.example.sample.logic.homepage.fragment;

import static com.example.sample.Constant.SESSION_ID;

import android.content.Intent;
import android.view.View;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sample.R;
import com.example.sample.data.SessionBean;
import com.example.sample.livebus.LiveBus;
import com.example.sample.logic.homepage.adapter.SessionListAdapter;
import com.example.sample.logic.p2pchat.SessionActivity;
import com.example.sample.mvvm.BaseFragment;
import com.example.sample.mvvm.ItemClickCallBack;
import com.example.sample.viewmodel.SessionViewModel;

import java.util.List;

public class ChatListFragment extends BaseFragment<SessionViewModel> {
    RecyclerView mRcvList;
    SessionListAdapter mListAdapter;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_chatlist;
    }

    @Override
    protected void initView(View root) {
        mRcvList = root.findViewById(R.id.rcv_list);

        mListAdapter = new SessionListAdapter(new ItemClickCallBack<SessionBean>() {
            @Override
            public void onItemClick(int position, SessionBean data) {
                Intent intent = new Intent(getActivity(), SessionActivity.class);
                intent.putExtra(SESSION_ID, data.getSessionId());
                startActivity(intent);
            }
        });
        mRcvList.setLayoutManager(new LinearLayoutManager(getContext()));
        mRcvList.setAdapter(mListAdapter);

    }

    @Override
    protected void initData() {
        mViewModel.chatList.observe(this, new Observer<List<SessionBean>>() {
            @Override
            public void onChanged(List<SessionBean> sessionBeans) {
                mListAdapter.refreshData(sessionBeans);
            }
        });

        LiveBus.getInstance().getMessageArr().observe(this, new Observer<SessionBean>() {
            @Override
            public void onChanged(SessionBean sessionBean) {
                mViewModel.getSessionList();
            }
        });
        mViewModel.getSessionList();
    }
}
