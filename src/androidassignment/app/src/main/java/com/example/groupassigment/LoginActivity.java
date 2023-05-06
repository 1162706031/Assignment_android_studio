package com.example.groupassigment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText mUsernameView; //用户名输入框
    private EditText mPasswordView; //密码输入框
    private Button mLoginButton; //登录按钮
    private ProgressBar mLoadingView; //加载视图

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mUsernameView = findViewById(R.id.username); //从布局文件中获取用户名输入框
        mPasswordView = findViewById(R.id.password); //从布局文件中获取密码输入框
        mLoginButton = findViewById(R.id.login); //从布局文件中获取登录按钮
        mLoadingView = findViewById(R.id.loading); //从布局文件中获取加载视图

        //为用户名输入框和密码输入框添加文本改变监听器
        mUsernameView.addTextChangedListener(mTextWatcher);
        mPasswordView.addTextChangedListener(mTextWatcher);

        //为登录按钮添加点击监听器
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptLogin(); //尝试登录
            }
        });
    }

    //文本改变监听器
    private TextWatcher mTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}

        //文本改变后，检查用户名和密码输入框是否都有输入内容，若有则启用登录按钮，否则禁用
        @Override
        public void afterTextChanged(Editable s) {
            if (mUsernameView.getText().length() > 0 && mPasswordView.getText().length() > 0) {
                mLoginButton.setEnabled(true);
            } else {
                mLoginButton.setEnabled(false);
            }
        }
    };

    //尝试登录方法
    private void attemptLogin() {
        String username = mUsernameView.getText().toString(); //获取输入的用户名
        String password = mPasswordView.getText().toString(); //获取输入的密码

        // TODO: Add your login logic here

        mLoadingView.setVisibility(View.VISIBLE); //显示加载视图
        mLoginButton.setEnabled(false); //禁用登录按钮






        // 模拟一个长时间的网络操作
        mUsernameView.postDelayed(new Runnable() {
            @Override
            public void run() {
                mLoadingView.setVisibility(View.GONE); //隐藏加载视图
                mLoginButton.setEnabled(true); //启用登录按钮

                // TODO: Replace this with your login result handling code
                boolean loginSuccessful = true;
                if (loginSuccessful) {
                    finish(); //结束当前Activity，返回上一个Activity
                }
            }
        }, 3000); //延时3秒
    }
}
