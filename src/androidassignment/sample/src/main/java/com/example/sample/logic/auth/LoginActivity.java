package com.example.sample.logic.auth;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.lifecycle.Observer;

import com.example.sample.databinding.ActivityAuthBinding;
import com.example.sample.logic.homepage.HomeActivity;
import com.example.sample.mvvm.BaseActivity;
import com.example.sample.utils.ToastUtils;
import com.example.sample.viewmodel.AuthViewModel;

public class LoginActivity extends BaseActivity<AuthViewModel, ActivityAuthBinding> {
    ActivityResultLauncher<Intent> mAtyResult;

    @Override
    protected void initData() {
        mAtyResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {

            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    // Handle the result
                    Intent data = result.getData();
                    mViewBinding.loginUsername.setText(data.getStringExtra("userEmail"));
                }
            }
        });
        mViewModel.loginResult.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    ToastUtils.show(getBaseContext(), "登录失败");
                }
            }
        });
    }

    @Override
    protected void initView() {
        mViewBinding.signupRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goSignUp();
            }
        });

        mViewBinding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = mViewBinding.loginUsername.getText().toString().trim();
                String pwd = mViewBinding.loginPassword.getText().toString().trim();
                if (TextUtils.isEmpty(user) || TextUtils.isEmpty(pwd)) {
                    ToastUtils.show(getBaseContext(), "empty user or pwd ！");
                    return;
                }
                mViewModel.login(user, pwd);
            }
        });


    }

    private void goSignUp() {
        mAtyResult.launch(new Intent(this, SignUpActivity.class));
    }

    @Override
    protected ActivityAuthBinding getViewBinding() {
        return ActivityAuthBinding.inflate(getLayoutInflater());
    }
}
