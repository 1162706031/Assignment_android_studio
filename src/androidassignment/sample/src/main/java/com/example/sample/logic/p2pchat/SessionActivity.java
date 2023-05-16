package com.example.sample.logic.p2pchat;

import static com.example.sample.Constant.SESSION_ID;

import android.graphics.Rect;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewTreeObserver;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.sample.R;
import com.example.sample.data.MsgBean;
import com.example.sample.data.SessionBean;
import com.example.sample.databinding.ActivitySessionBinding;
import com.example.sample.livebus.LiveBus;
import com.example.sample.mvvm.BaseActivity;
import com.example.sample.viewmodel.SessionViewModel;

import java.util.List;

public class SessionActivity extends BaseActivity<SessionViewModel, ActivitySessionBinding> {
    MsgAdapter mAdapter;

    @Override
    protected ActivitySessionBinding getViewBinding() {
        return ActivitySessionBinding.inflate(getLayoutInflater());
    }


    @Override
    protected void initData() {
        String sessionId = getIntent().getStringExtra(SESSION_ID);
        mViewModel.title.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                mViewBinding.tvSeesionName.setText(s);
            }
        });
        mViewModel.recordList.observe(this, new Observer<List<MsgBean>>() {
            @Override
            public void onChanged(List<MsgBean> msgBeans) {
                mAdapter.refreshData(msgBeans);
                mViewBinding.rcvList.scrollToPosition(mAdapter.getItemCount() - 1);
            }
        });

        LiveBus.getInstance().getMessageArr().observe(this, new Observer<SessionBean>() {
            @Override
            public void onChanged(SessionBean sessionBean) {
                if (sessionBean.getSessionId().equals(sessionId)) {
                    mViewModel.getSessionRecord(sessionId);
                }
            }
        });

        //获取聊天记录
        mViewModel.getSessionRecord(sessionId);
    }

    @Override
    protected void initView() {
        mAdapter = new MsgAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mViewBinding.rcvList.setLayoutManager(linearLayoutManager);

        mViewBinding.rcvList.setAdapter(mAdapter);
        findViewById(R.id.btn_send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = mViewBinding.etText.getText().toString();
                mViewBinding.etText.setText("");
                if (!TextUtils.isEmpty(s)) {
                    mViewModel.sendMsg(s);
                }
            }
        });

        mViewBinding.rcvList.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect r = new Rect();
                mViewBinding.rcvList.getWindowVisibleDisplayFrame(r);
                int screenHeight = mViewBinding.rcvList.getRootView().getHeight();
                int keypadHeight = screenHeight - r.bottom;
                if (keypadHeight > screenHeight * 0.15) {
                    mViewBinding.rcvList.scrollToPosition(mAdapter.getItemCount() - 1);
                }
            }
        });

    }
}
