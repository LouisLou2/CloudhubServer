package com.cloudhub.normal.service.validator;

import com.cloudhub.normal.model.storage.MiniInfo;

import java.util.List;
public interface FileOperateValidator {
    long SpaceEnoughCheck(long userId,long requestSize);
    long SpaceEnoughCheck(long userId, List<MiniInfo> list);
    boolean MoveCheck(long id,List<MiniInfo>list);
    boolean isFileExist(long id);
}
