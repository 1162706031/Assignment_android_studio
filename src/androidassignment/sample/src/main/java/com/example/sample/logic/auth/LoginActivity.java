package com.example.sample.logic.auth;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.lifecycle.Observer;

import com.example.sample.R;
import com.example.sample.logic.homepage.HomeActivity;
import com.example.sample.mvvm.BaseActivity;
import com.example.sample.utils.ToastUtils;
import com.example.sample.viewmodel.AuthViewModel;

public class LoginActivity extends BaseActivity<AuthViewModel> {
    EditText mEtUserName;
    EditText mEtPwd;
    Button mBtnLogin;
    ActivityResultLauncher<Intent> mAtyResult;

    @Override
    protected void initData() {
        mAtyResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {

            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    // Handle the result
                    Intent data = result.getData();
                    mEtUserName.setText(data.getStringExtra("userEmail"));
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
        mEtUserName = findViewById(R.id.login_username);
        mEtPwd = findViewById(R.id.login_password);
        mBtnLogin = findViewById(R.id.login_button);
        findViewById(R.id.signupRedirectText).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goSignUp();
            }
        });

        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = mEtUserName.getText().toString().trim();
                String pwd = mEtPwd.getText().toString().trim();
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
    protected int setLayoutId() {
        return R.layout.activity_auth;
    }
}
