package com.cloudhub.normal.model.storage;

public class BaseInfo extends MiniInfo {
    private long userId;
    private String name;
    private long timeStamp;
    private long pid;
    /**
     * 所有祖先目录 id
     */

    //constructors
    public BaseInfo() {
        super(0L, 0);
        //默认构造函数将设置默认值
        this.userId = 0;
        this.pid = 0L;
        this.name = "root";
        this.timeStamp = 0L;
    }
    public BaseInfo(long id,int type,long userId,String name, long timeStamp, long pid) {
        super(id, type);
        this.userId = userId;
        this.pid = pid;
        this.name = name;
        this.timeStamp = timeStamp;
    }

    //all setters and getters
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
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