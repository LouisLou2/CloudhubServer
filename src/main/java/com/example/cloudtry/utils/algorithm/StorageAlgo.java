package com.example.cloudtry.utils.algorithm;

import com.example.cloudtry.dao.FileDao;
import com.example.cloudtry.model.storage.MiniInfo;

import java.util.List;
import java.util.Objects;

public class StorageAlgo {
    public static long getFolderSize(long folderId){
        List<MiniInfo> list= FileDao.getMiniChildList(folderId);
        return getListSize_inner(list,0);
    }
    public static long getListSize(List<MiniInfo> list){
        return getListSize_inner(list,0);
    }
    private static long getListSize_inner(List<MiniInfo> list, long size){
        for(MiniInfo info:list){
            if(info.getType()==0){
                size+=getListSize_inner(Objects.requireNonNull(FileDao.getMiniChildList(info.getId())),size);
            }else{
                size+=FileDao.getFileSize(info.getId());
            }
        }
        return size;
    }

}
