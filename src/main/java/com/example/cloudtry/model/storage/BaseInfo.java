package com.example.cloudtry.model.storage;

public class BaseInfo {
    private long id;
    /**
     * 父级目录 id(parent folder id)
     */
    private long userId;
    private String name;
    private long timeStamp;
    private long pid;
    /**
     * 所有祖先目录 id
     */
    private String ancestors;

    //constructors
    public BaseInfo() {
        //默认构造函数将设置默认值
        this.userId = 0;
        this.id = 0L;
        this.pid = 0L;
        this.name = "root";
        this.ancestors = "0";
        this.timeStamp = 0L;
    }
    public BaseInfo(long id,long userId,String name, long timeStamp, long pid,  String ancestors) {
        this.userId = userId;
        this.id = id;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPid() {
        return pid;
    }

    public void setPid(long pid) {
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