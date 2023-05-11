package com.example.groupassigment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sample.logic.homepage.HomeActivity;

public class WelcomeActivity extends AppCompatActivity {
    // 定义欢迎页面的时间，单位为毫秒
    private static final int WELCOME_TIME = 2000;
    // 定义欢迎页面是否已经显示的标志
    private boolean isShowed = false;
    // 定义一个HandlerThread对象
    private HandlerThread mHandlerThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        // 创建HandlerThread对象，将它的名字设置为"WelcomeThread"
        mHandlerThread = new HandlerThread("WelcomeThread");
        // 启动HandlerThread
        mHandlerThread.start();

        // 使用Handler在HandlerThread中进行延时操作
        Handler handler = new Handler(mHandlerThread.getLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!isShowed) {
                    // 跳转到MainActivity
//                    Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                    Intent intent = new Intent(WelcomeActivity.this, HomeActivity.class);
                    startActivity(intent);
                    // 结束当前Activity
                    finish();
                    isShowed = true;
                }
            }
        }, WELCOME_TIME);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 结束HandlerThread
        mHandlerThread.quit();
    }
}

