package com.example.pacman_bytes;

public class NotamData {

    private String mSubarea,mArea,mSubject,mNotam;

    public NotamData(String mSubarea, String mArea, String mSubject, String mNotam) {
        this.mSubarea = mSubarea;
        this.mArea = mArea;
        this.mSubject = mSubject;
        this.mNotam = mNotam;
    }

    public String getmSubarea() {
        return mSubarea;
    }

    public String getmArea() {
        return mArea;
    }

    public String getmSubject() {
        return mSubject;
    }

    public String getmNotam() {
        return mNotam;
    }
}
