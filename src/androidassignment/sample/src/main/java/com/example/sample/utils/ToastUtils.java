package com.example.sample.utils;

import android.content.Context;
import android.widget.Toast;

public class ToastUtils {
    public static void show(Context context, String s) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }
}
