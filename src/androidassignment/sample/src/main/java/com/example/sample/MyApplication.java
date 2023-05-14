package com.example.sample;

import android.app.Application;

import com.example.sample.data.UserInfoBean;
import com.tencent.mmkv.MMKV;

import java.io.File;

public class MyApplication extends Application {

    public static UserInfoBean user1;
    public static MMKV mmkv;

    /**
     * 初始化登录用户，初始化用户本地存储空间
     *
     * @param user
     */
    public static void setUserLogin(UserInfoBean user) {
        user1 = user;

        String userDir = MMKV.getRootDir() + File.separator + user1.getEmail();
        mmkv = MMKV.mmkvWithID(user1.getEmail(), MMKV.MULTI_PROCESS_MODE, userDir);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        /**
         * init sdk here
         */
        MMKV.initialize(this);

    }
}
