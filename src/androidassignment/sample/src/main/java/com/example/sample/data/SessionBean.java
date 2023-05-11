package com.example.sample.data;

public class SessionBean {
    String sessionId;
    String sesionName;
    String updateTime;
    String lastMsg;
    int newMsg;

    public String getLastMsg() {
        return lastMsg;
    }

    public void setLastMsg(String lastMsg) {
        this.lastMsg = lastMsg;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getSesionName() {
        return sesionName;
    }

    public void setSesionName(String sesionName) {
        this.sesionName = sesionName;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public int getNewMsg() {
        return newMsg;
    }

    public void setNewMsg(int newMsg) {
        this.newMsg = newMsg;
    }
}
