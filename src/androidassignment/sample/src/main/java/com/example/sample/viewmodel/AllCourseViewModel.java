package com.example.sample.viewmodel;

import android.text.TextUtils;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.sample.data.CourseBean;

import java.util.ArrayList;
import java.util.List;

public class AllCourseViewModel extends ViewModel {
    public MutableLiveData<List<CourseBean>> allCourseResult = new MutableLiveData<>();

    public void searchAllCourse(String content) {
        //为空则全部展示
        if (TextUtils.isEmpty(content)) {

        } else {//根据内容搜索

        }

        List<CourseBean> datalist = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            CourseBean item = new CourseBean();
            item.setCourseName("Course" + i);
            item.setTeacherName("sam" + i);
            datalist.add(item);
        }
        allCourseResult.setValue(datalist);
    }
}
