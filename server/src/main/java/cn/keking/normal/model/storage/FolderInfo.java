package cn.keking.normal.model.storage;

import cn.keking.normal.common.enums.BaseTypeEnum;

public class FolderInfo extends BaseInfo{

    public FolderInfo() {
        super();
    }

    public FolderInfo(long userId, long id, long pid, String name,long timeStamp) {
        super(id, BaseTypeEnum.FOLDER,userId, name,timeStamp,pid);
    }
}
