package com.example.sample.data;

import java.io.Serializable;

public class ClassMates implements Serializable {
    String uid;
    String name;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
