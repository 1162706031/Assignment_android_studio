package com.example.sample.livebus;

import androidx.lifecycle.MutableLiveData;

import com.example.sample.data.SessionBean;

public class LiveBus {
    static class Inner {
        public static LiveBus instance = new LiveBus();
    }

    public static LiveBus getInstance() {
        return Inner.instance;
    }


    MutableLiveData<SessionBean> MSG_ARR = new MutableLiveData<>();

    public MutableLiveData<SessionBean> getMessageArr() {
        return MSG_ARR;
    }

    public void messageArrived(SessionBean data) {
        MSG_ARR.setValue(data);
    }
}
