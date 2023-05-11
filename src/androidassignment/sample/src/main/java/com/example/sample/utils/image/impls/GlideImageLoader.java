package com.example.sample.utils.image.impls;

import android.app.Activity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.sample.R;
import com.example.sample.utils.lifecycle.ActivityExt;


public class GlideImageLoader {

    private static final String TAG = "GlideImageLoader";


    public static void load(ImageView imageView, String url) {
        Activity activity = ActivityExt.getActivityFromView(imageView);
        if (ActivityExt.isActivityDestroyed(activity)) {
            return;
        }
        Glide.with(activity)
                .load(url)
                .apply(RequestOptions.errorOf(R.drawable.icon_image_null))
                .into(imageView);
    }


}
