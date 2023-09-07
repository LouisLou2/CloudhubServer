package com.example.cloudtry.model.storage;

import com.example.cloudtry.common.constant.enums.FileTypeEnum;

public class FileInfo extends BaseInfo {
    private String fileId;//fileId就是文件的哈希码，仅与文件的字节内容有关
    private long size;
    private FileTypeEnum type;
    private String bucketName;
    private String objectKey;

    //constructor
    public FileInfo() {
    }
    public FileInfo(String unqId,long userId,String fileId, String name, long size, FileTypeEnum type,long timeStamp, String pid, String ancestors, String bucketName, String objectKey) {
        super(unqId,userId, name,timeStamp,pid,ancestors);
        this.fileId = fileId;
        this.bucketName = bucketName;
        this.objectKey = objectKey;
    }
    public String getUnqId() {
        return super.getUnqId();
    }

    public void setUnqId(String unqId) {
        super.setUnqId(unqId);
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
    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
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
