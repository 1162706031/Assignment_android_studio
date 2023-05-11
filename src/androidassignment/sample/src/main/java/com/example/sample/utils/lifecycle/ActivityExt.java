package com.example.sample.utils.lifecycle;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;
import android.view.View;

/**
 * Created by louisgeek on 2018/6/21.
 */
public class ActivityExt {
    //判断 Activity 是否 Destroyed
    public static boolean isActivityDestroyed(final Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            return activity == null || activity.isFinishing() || activity.isDestroyed();
        } else {
            return activity == null || activity.isFinishing();
        }
    }

    public static Activity getActivityFromContext(Context context) {
        if (context != null) {
            //循环尝试获取aty
            while (context instanceof ContextWrapper) {
                if (context instanceof Activity) {
                    Activity activity = (Activity) context;
                    return activity;
                }
                ContextWrapper contextWrapper = (ContextWrapper) context;
                context = contextWrapper.getBaseContext();
            }
        }
        return null;
    }

    public static Activity getActivityFromView(final View view) {
        if (view != null) {
            Context context = view.getContext();
            //循环尝试获取aty
            while (context instanceof ContextWrapper) {
                if (context instanceof Activity) {
                    Activity activity = (Activity) context;
                    return activity;
                }
                ContextWrapper contextWrapper = (ContextWrapper) context;
                context = contextWrapper.getBaseContext();
            }
        }
        return null;

    }

}
