package com.example.sample.data;

import java.io.Serializable;
import java.util.List;

public class UserInfoBean implements Serializable {
    List<CourseBean> courses;

    String uid;
    String displayName;
    String major;
    String headUrl;

    String email;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<CourseBean> getCourses() {
        return courses;
    }

    public void setCourses(List<CourseBean> courses) {
        this.courses = courses;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }
}
