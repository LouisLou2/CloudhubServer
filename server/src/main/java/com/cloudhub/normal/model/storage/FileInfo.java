package com.cloudhub.normal.model.storage;

public class FileInfo extends BaseInfo {
    private String hash;//fileId就是文件的哈希码，仅与文件的字节内容有关
    private long size;
    private int type;

    //constructor
    public FileInfo() {
    }
    public FileInfo(long id,long userId,String hash, String name, long size, int type,long timeStamp, long pid) {
        super(id, 1, userId,name,timeStamp,pid);
        this.hash = hash;
        this.size = size;
        this.type = type;
    }
    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

}
