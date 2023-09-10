package com.example.cloudtry.dao;

import com.alibaba.fastjson.JSONObject;
import com.example.cloudtry.model.storage.BaseInfo;
import com.example.cloudtry.model.storage.MiniInfo;

import java.util.List;

public class FileDao {
    public static boolean isFileExist(long id){
        return false;
    }
    /*若是此id找不到，则返回null*/
    public static JSONObject getBucketNameAndObjectKey(long id){
        return null;
    }
    public static List<MiniInfo> getMiniChildList(long folderId){
        return null;
    }

    /*getRootList返回的是BaseInfo的引用，但是每个BaseInfo要引用FileInfo或FolderInfo(父类引用子类)*/
    public static List<BaseInfo> getRootListById(long userId){
        return null;
    }
    public static List<BaseInfo> getRootListByName(String name){
        return null;
    }
    public static void changeParent(List<MiniInfo>childs, long newPid){

    }
    public static void addChilds(long id, List<MiniInfo>childs){

    }
    public static long getPid(long id){
        return -1L;
    }
    public static boolean isChildOf(long id, long pid){
        return false;
    }
    /*id是list中任何一个的孩子都返回true*/
    public static boolean  isChildOfAny(long id, List<MiniInfo>list){
        return false;
    }
    public static boolean isDescendantOf(long id, long pid){
        long apid=id;
        do{
            apid=getPid(apid);
        }while(apid!=0L&&apid!=pid);
        return apid==pid;
    }
    /*id是list中任何一个的后代都返回true*/
    public static boolean isDescendantOfAny(long id, List<MiniInfo>list){
        for(MiniInfo info:list){
            if(isDescendantOf(id,info.getId())){
                return true;
            }
        }
        return false;
    }
    public static long getFileSize(long fileId){

        return fileId;
    }
    public static long getOccupation(long userId){
        return 0L;
    }
    public static long getRestSpace(long userId){
        return 0L;
    }
    public static long getTotalSpace(long userId){
        return 0L;
    }
}
