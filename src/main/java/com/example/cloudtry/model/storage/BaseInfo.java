package com.example.cloudtry.model.storage;

public class BaseInfo {
    private String unqId;
    /**
     * 父级目录 id(parent folder id)
     */
    private long userId;
    private String name;
    private long timeStamp;
    private String pid;
    /**
     * 所有祖先目录 id
     */
    private String ancestors;

    //constructors
    public BaseInfo() {
        //默认构造函数将设置默认值
        this.userId = 0;
        this.unqId = "0";
        this.pid = "0";
        this.name = "root";
        this.ancestors = "0";
        this.timeStamp = 0L;
    }
    public BaseInfo(String unqId,long userId,String name, long timeStamp, String pid,  String ancestors) {
        this.userId = userId;
        this.unqId = unqId;
        this.pid = pid;
        this.name = name;
        this.ancestors = ancestors;
        this.timeStamp = timeStamp;
    }

    //all setters and getters
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUnqId() {
        return unqId;
    }

    public void setUnqId(String unqId) {
        this.unqId = unqId;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public BaseInfo setName(String name) {
        this.name = name;
        return this;
    }

    public String getAncestors() {
        return ancestors;
    }

    public void setAncestors(String ancestors) {
        this.ancestors = ancestors;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }
    @Override
    public String toString() {
        return "";
    }
}