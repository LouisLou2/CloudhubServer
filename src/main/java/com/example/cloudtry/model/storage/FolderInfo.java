package com.example.cloudtry.model.storage;

public class FolderInfo extends BaseInfo{

    public FolderInfo() {
        super();
    }

    public FolderInfo(long userId, String unqId, String pid, String name, String ancestors,long timeStamp) {
        super(unqId,userId, name,timeStamp,pid,ancestors);
    }
}
