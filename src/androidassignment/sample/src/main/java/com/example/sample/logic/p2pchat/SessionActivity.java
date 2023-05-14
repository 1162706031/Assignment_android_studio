package com.example.sample.logic.p2pchat;

import static com.example.sample.Constant.SESSION_ID;

import android.graphics.Rect;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.TextView;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sample.R;
import com.example.sample.data.MsgBean;
import com.example.sample.data.SessionBean;
import com.example.sample.livebus.LiveBus;
import com.example.sample.mvvm.BaseActivity;
import com.example.sample.viewmodel.SessionViewModel;

import java.util.List;

public class SessionActivity extends BaseActivity<SessionViewModel> {
    RecyclerView mRcv;
    MsgAdapter mAdapter;
    EditText mEtCotent;
    TextView mTvTitle;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_session;
    }


    @Override
    protected void initData() {
        String sessionId = getIntent().getStringExtra(SESSION_ID);
        mViewModel.title.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                mTvTitle.setText(s);
            }
        });
        mViewModel.recordList.observe(this, new Observer<List<MsgBean>>() {
            @Override
            public void onChanged(List<MsgBean> msgBeans) {
                mAdapter.refreshData(msgBeans);
                mRcv.scrollToPosition(mAdapter.getItemCount() - 1);
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
        mRcv = findViewById(R.id.rcv_list);
        mTvTitle = findViewById(R.id.tv_seesion_name);
        mEtCotent = findViewById(R.id.et_text);
        mAdapter = new MsgAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
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

        mRcv.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect r = new Rect();
                mRcv.getWindowVisibleDisplayFrame(r);
                int screenHeight = mRcv.getRootView().getHeight();
                int keypadHeight = screenHeight - r.bottom;
                if (keypadHeight > screenHeight * 0.15) {
                    mRcv.scrollToPosition(mAdapter.getItemCount() - 1);
                }
            }
        });

    }
}
