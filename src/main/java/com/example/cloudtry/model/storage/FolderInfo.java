package com.example.cloudtry.model.storage;

public class FolderInfo extends BaseInfo{

    public FolderInfo() {
        super();
    }

    public FolderInfo(long userId, long id, long pid, String name, String ancestors,long timeStamp) {
        super(id,userId, name,timeStamp,pid,ancestors);
    }
}
