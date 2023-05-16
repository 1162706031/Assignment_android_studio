package com.example.sample.data;

import java.io.Serializable;

public class ClassMates implements Serializable {
    String email;
    String displayName;

    public String getEmail() {
        return email;
    }

    public void setEmail(String uid) {
        this.email = uid;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String name) {
        this.displayName = name;
    }
}
