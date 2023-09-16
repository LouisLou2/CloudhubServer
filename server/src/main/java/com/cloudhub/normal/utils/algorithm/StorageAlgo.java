package com.cloudhub.normal.utils.algorithm;

import com.cloudhub.normal.common.enums.BaseTypeEnum;
import com.cloudhub.normal.dao.FileDao;
import com.cloudhub.normal.model.storage.MiniInfo;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

public class StorageAlgo {
    @Resource
    private static FileDao fileDao;
    public static long getFolderSize(long folderId){
        List<MiniInfo> list= fileDao.getMiniChildList(folderId);
        return getListSize_inner(list,0);
    }
    public static long getListSize(List<MiniInfo> list){
        return getListSize_inner(list,0);
    }
    private static long getListSize_inner(List<MiniInfo> list, long size){
        for(MiniInfo info:list){
            if(info.getBaseType()== BaseTypeEnum.FOLDER){
                size+=getListSize_inner(Objects.requireNonNull(fileDao.getMiniChildList(info.getId())),size);
            }else{
                size+=fileDao.getSizeByFileId(info.getId());
            }
        }
        return size;
    }

}
