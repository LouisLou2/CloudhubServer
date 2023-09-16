package com.cloudhub.normal.dao;

import com.cloudhub.normal.model.storage.FolderInfo;
import com.cloudhub.normal.model.storage.MiniInfo;

import java.util.List;

public interface FolderDao {
    long getPidByFolderId(long id);
    List<FolderInfo> getRootFolder(String userName);
    List<FolderInfo> getChildrenFolder(long id);
    void creatNewFolder(FolderInfo folder);
    void createNewFolder(long id, String name,long pid,long timeStamp);
    void deleteFolderByFolderId(long id);
    boolean checkFolderExist(long pid,String name);
    void updateFolderName(long pid,String name);
    void changeFolderParent(long id, long newPid);
    boolean isChildOf(long id, long pid);
    boolean isDescendantOf(long id, long pid);
    boolean isDescendantOfAny(long id, long[] pids);
    boolean isDescendantOfAny(long id, List<MiniInfo>list);
}
