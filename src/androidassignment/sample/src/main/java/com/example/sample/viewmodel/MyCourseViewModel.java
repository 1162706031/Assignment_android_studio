package com.example.sample.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.sample.data.CourseBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyCourseViewModel extends ViewModel {

    //我的所有课程 catalog - list  方式存储
    private Map<String, List<CourseBean>> myCourseMap = new HashMap<>();

    /**
     * 当前显示的课程列表
     */
    public MutableLiveData<List<CourseBean>> currentCourseList = new MutableLiveData<>();

    /**
     * 当前显示的课程index
     */
    public MutableLiveData<List<String>> indexList = new MutableLiveData<>();

    public void catalogSelected(String catalog) {
        List<CourseBean> datalist = myCourseMap.get(catalog);
        if (datalist == null) {
            datalist = new ArrayList<>(0);
        }
        currentCourseList.setValue(datalist);
    }

    public void getAllMyCourseFromDatabase() {
        List<CourseBean> datalist = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 30; j++) {
                CourseBean item = new CourseBean();
                item.setCourseName("Course" + j);
                item.setTeacherName("sam" + j);
                item.setCatalog("catalog" + i);
                datalist.add(item);
            }
        }
        //change it to map
        myCourseMap.clear();
        for (CourseBean item : datalist) {
            List<CourseBean> courseBeans = myCourseMap.get(item.getCatalog());
            if (courseBeans == null) {
                courseBeans = new ArrayList<>();
                myCourseMap.put(item.getCatalog(), courseBeans);
            }
            courseBeans.add(item);
        }


        List<String> index = new ArrayList<>(myCourseMap.keySet());
        indexList.setValue(index);

    }
}
