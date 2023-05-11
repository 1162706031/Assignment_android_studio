package com.example.sample.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.sample.data.SessionBean;

import java.util.ArrayList;
import java.util.List;

public class SessionViewModel extends ViewModel {

    public MutableLiveData<List<SessionBean>> currentCourseList = new MutableLiveData<>();


    public void getSessionHis() {
        List<SessionBean> datalist = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            SessionBean item = new SessionBean();
            item.setSesionName("会话" + i);
            item.setLastMsg("最后一次说" + i);
            item.setUpdateTime("time" + i);
            datalist.add(item);
        }
        currentCourseList.setValue(datalist);
    }
}
