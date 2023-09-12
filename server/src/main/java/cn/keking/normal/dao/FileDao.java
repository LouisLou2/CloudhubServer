package cn.keking.normal.dao;

import cn.keking.normal.model.storage.FileInfo;
import cn.keking.normal.model.storage.MiniInfo;
import com.alibaba.fastjson.JSONObject;

import java.util.List;

public interface FileDao {
    boolean isFileExist(long id);
    boolean isSameNameExistInFolder(long pid,String name);
    long getSizeByFileId(long id);
    List<MiniInfo> getMiniChildList(long id);
    List<FileInfo> getFileInfoByFolderId(long id);
    void addFile(FileInfo file);
    void ReceiveFileById(long userId,long originFileId,long ownFileId);
    void deleteFile(long id);
    void changeParent(List<MiniInfo>childs, long newPid);
    FileInfo getFileInfoById(long id);
    List<FileInfo> getRootFileList(String name);
    List<FileInfo>  getFileInfoListByFolderId(long id);
    void updateFileName(long id,String fileName);
    JSONObject getBucketNameAndObjectKey(long id);
    long getPidByFileId(long id);
    String getHashByFileId(long id);
    void changeFileParent(long id, long newPid);
    boolean isChildOf(long id, long pid);
    boolean isDescendantOf(long id, long pid);
    boolean isDescendantOfAny(long id, long[] pids);
    boolean isDescendantOfAny(long id, List<MiniInfo>list);
}
