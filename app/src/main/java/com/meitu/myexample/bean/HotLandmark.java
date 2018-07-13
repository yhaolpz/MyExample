package com.meitu.myexample.bean;

/**
 * Created by wyh3 on 2018/4/10.
 */
public class HotLandmark {
    private String name;
    private int unlockNum;
    private int contentNum;
    private String coverUrl;
    private boolean lock;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUnlockNum() {
        return unlockNum;
    }

    public void setUnlockNum(int unlockNum) {
        this.unlockNum = unlockNum;
    }

    public int getContentNum() {
        return contentNum;
    }

    public void setContentNum(int contentNum) {
        this.contentNum = contentNum;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public boolean isLock() {
        return lock;
    }

    public void setLock(boolean lock) {
        this.lock = lock;
    }
}
