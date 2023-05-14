package com.example.sample.logic.auth;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.lifecycle.Observer;

import com.example.sample.R;
import com.example.sample.mvvm.BaseActivity;
import com.example.sample.utils.ToastUtils;
import com.example.sample.viewmodel.AuthViewModel;

public class SignUpActivity extends BaseActivity<AuthViewModel> {
    EditText mEtUserName, mEtEmail;
    EditText mEtPwd, mEtPwdC;
    Button mBtnSign;
    TextView mTvInfo;


    @Override
    protected void initData() {
        mViewModel.signUpResult.observe(this, new Observer<Pair<Boolean, String>>() {
            @Override
            public void onChanged(Pair<Boolean, String> booleanStringPair) {
                if (booleanStringPair.first) {
                    ToastUtils.show(SignUpActivity.this, "success");
                    Intent i = new Intent();
                    i.putExtra("userEmail", booleanStringPair.second);
                    setResult(RESULT_OK, i);
                    finish();
                } else {
                    mTvInfo.setText(booleanStringPair.second);
                }
            }
        });
    }

    @Override
    protected void initView() {
        mEtUserName = findViewById(R.id.et_userName);
        mEtEmail = findViewById(R.id.et_email);
        mEtPwd = findViewById(R.id.et_pwd);
        mEtPwdC = findViewById(R.id.et_pwd_c);
        mBtnSign = findViewById(R.id.btn_sign);
        mTvInfo = findViewById(R.id.tv_info);

        mBtnSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = mEtEmail.getText().toString().trim();
                String user = mEtUserName.getText().toString().trim();
                String pwd = mEtPwd.getText().toString().trim();
                String pwdc = mEtPwdC.getText().toString().trim();
                if (TextUtils.isEmpty(user)
                        || TextUtils.isEmpty(email)
                        || TextUtils.isEmpty(pwd)
                        || TextUtils.isEmpty(pwdc)) {
                    mTvInfo.setText("empty user or pwd or email！");
                    return;
                }
                if (!pwd.equals(pwdc)) {
                    mTvInfo.setText("check the password");
                    return;
                }
                mViewModel.signUp(user, email, pwd);
            }
        });


    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_sign;
    }
}
