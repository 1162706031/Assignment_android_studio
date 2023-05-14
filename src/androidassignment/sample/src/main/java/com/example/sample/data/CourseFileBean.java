package com.example.sample.data;

import java.io.Serializable;

public class CourseFileBean implements Serializable {
    int fileType;
    String fileUrl;

    public int getFileType() {
        return fileType;
    }

    public void setFileType(int fileType) {
        this.fileType = fileType;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }
}
