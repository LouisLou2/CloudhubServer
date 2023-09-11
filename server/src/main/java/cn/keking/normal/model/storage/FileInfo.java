package cn.keking.normal.model.storage;

import cn.keking.normal.common.enums.BaseTypeEnum;
import cn.keking.normal.common.enums.FileTypeEnum;

public class FileInfo extends BaseInfo {
    private String hash;//fileId就是文件的哈希码，仅与文件的字节内容有关
    private long size;
    private FileTypeEnum type;
    private String bucketName;
    private String objectKey;

    //constructor
    public FileInfo() {
    }
    public FileInfo(long id,long userId,String hash, String name, long size, FileTypeEnum type,long timeStamp, long pid,String bucketName, String objectKey) {
        super(id, BaseTypeEnum.FILE, userId,name,timeStamp,pid);
        this.hash = hash;
        this.size = size;
        this.type = type;
        this.bucketName = bucketName;
        this.objectKey = objectKey;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public FileTypeEnum getType() {
        return type;
    }

    public void setType(FileTypeEnum type) {
        this.type = type;
    }
    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getBucketName() {
        return bucketName;
    }

    public String getObjectKey() {
        return objectKey;
    }

    public void setObjectKey(String objectKey) {
        this.objectKey = objectKey;
    }
}
