package com.example.cloudtry.service.validator;

import com.example.cloudtry.dao.FileDao;
import com.example.cloudtry.model.storage.MiniInfo;
import com.example.cloudtry.utils.algorithm.StorageAlgo;

import java.util.List;

public class FileOperateValidator {
    /**
     * 检查用户空间是否足够
     * @param userId
     * @param requestSize
     * @return 返回-1表示空间不足，否则返回新的占用空间大小
     */
    public static long SpaceEnoughCheck(long userId,long requestSize){
        long occupation=FileDao.getOccupation(userId);
        long totalSpace=FileDao.getTotalSpace(userId);
        if(totalSpace-occupation<requestSize){
            return -1L;
        }
        else return occupation+requestSize;
    }

    /**
     * 检查用户空间是否足够
     * @param userId
     * @param list
     * @return
     */
    public static long SpaceEnoughCheck(long userId, List<MiniInfo>list){
        long requestSize=StorageAlgo.getListSize(list);
        return SpaceEnoughCheck(userId,requestSize);
    }

    public static boolean MoveCheck(long id,List<MiniInfo>list){
        return !FileDao.isDescendantOfAny(id,list);
    }
    public static boolean isFileExist(long id){
        return FileDao.isFileExist(id);
    }
}
