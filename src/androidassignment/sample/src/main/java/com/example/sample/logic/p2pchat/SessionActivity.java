package com.example.sample.logic.p2pchat;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sample.R;
import com.example.sample.data.MsgBean;
import com.example.sample.mvvm.BaseActivity;
import com.example.sample.viewmodel.SessionViewModel;

import java.util.List;

public class SessionActivity extends BaseActivity<SessionViewModel> {
    RecyclerView mRcv;
    MsgAdapter mAdapter;
    EditText mEtCotent;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_session;
    }


    @Override
    protected void initData() {
        mRcv = findViewById(R.id.rcv_list);
        mEtCotent = findViewById(R.id.et_text);
        mAdapter = new MsgAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        linearLayoutManager.setStackFromEnd(true);
        mRcv.setLayoutManager(linearLayoutManager);

        mRcv.setAdapter(mAdapter);
        findViewById(R.id.btn_send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = mEtCotent.getText().toString();
                mEtCotent.setText("");
                if (!TextUtils.isEmpty(s)) {
                    mViewModel.sendMsg(s);
                }
            }
        });
    }

    @Override
    protected void initView() {
        mViewModel.recordList.observe(this, new Observer<List<MsgBean>>() {
            @Override
            public void onChanged(List<MsgBean> msgBeans) {
                mAdapter.refreshData(msgBeans);
                mRcv.scrollToPosition(mAdapter.getItemCount() - 1);
            }
        });

        mViewModel.getSessionRecord();
    }
}
