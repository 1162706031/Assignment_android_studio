package com.example.sample.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.sample.data.MsgBean;
import com.example.sample.data.SessionBean;

import java.util.ArrayList;
import java.util.List;

public class SessionViewModel extends ViewModel {

    public MutableLiveData<List<SessionBean>> chatList = new MutableLiveData<>();


    public void getSessionHis() {
        List<SessionBean> datalist = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            SessionBean item = new SessionBean();
            item.setSesionName("会话" + i);
            item.setLastMsg("最后一次说" + i);
            item.setUpdateTime("time" + i);
            datalist.add(item);
        }
        chatList.setValue(datalist);
    }

    public MutableLiveData<List<MsgBean>> recordList = new MutableLiveData<>();
    private List<MsgBean> recordLocal = new ArrayList<>();

    public void getSessionRecord() {
        if (recordLocal.isEmpty()) {
            for (int i = 0; i < 20; i++) {
                MsgBean item = new MsgBean("说了什么什么什么什么什么什么什么什么什么什么什么什么什么什么什么什么什么" + i);
                recordLocal.add(item);
            }
        }

        recordList.setValue(recordLocal);
    }

    public void sendMsg(String s) {
        MsgBean item = new MsgBean(s);
        recordLocal.add(item);
        recordList.setValue(recordLocal);
    }
}
