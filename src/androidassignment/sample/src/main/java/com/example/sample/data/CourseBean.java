package com.example.sample.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CourseBean implements Serializable {

    String courseId;
    String coverUrl;

    String catalog;
    String courseName;
    String teacherName;
    String des;

    List<CourseFileBean> files = new ArrayList<>();

    List<ClassMates> classMates = new ArrayList<>();

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public List<CourseFileBean> getFiles() {
        return files;
    }

    public void setFiles(List<CourseFileBean> files) {
        this.files = files;
    }

    public List<ClassMates> getClassMates() {
        return classMates;
    }

    public void setClassMates(List<ClassMates> classMates) {
        this.classMates = classMates;
    }
}
