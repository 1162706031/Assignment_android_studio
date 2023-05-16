package com.example.sample.logic.auth;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Pair;
import android.view.View;

import androidx.lifecycle.Observer;

import com.example.sample.databinding.ActivitySignBinding;
import com.example.sample.mvvm.BaseActivity;
import com.example.sample.utils.ToastUtils;
import com.example.sample.viewmodel.AuthViewModel;

public class SignUpActivity extends BaseActivity<AuthViewModel, ActivitySignBinding> {

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
                    mViewBinding.tvInfo.setText(booleanStringPair.second);
                }
            }
        });
    }

    @Override
    protected void initView() {
        mViewBinding.btnSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = mViewBinding.etEmail.getText().toString().trim();
                String user = mViewBinding.etUserName.getText().toString().trim();
                String pwd = mViewBinding.etPwd.getText().toString().trim();
                String pwdc = mViewBinding.etPwdC.getText().toString().trim();
                if (TextUtils.isEmpty(user)
                        || TextUtils.isEmpty(email)
                        || TextUtils.isEmpty(pwd)
                        || TextUtils.isEmpty(pwdc)) {
                    mViewBinding.tvInfo.setText("empty user or pwd or email！");
                    return;
                }
                if (!pwd.equals(pwdc)) {
                    mViewBinding.tvInfo.setText("check the password");
                    return;
                }
                mViewModel.signUp(user, email, pwd);
            }
        });


    }

    @Override
    protected ActivitySignBinding getViewBinding() {
        return ActivitySignBinding.inflate(getLayoutInflater());
    }
}
