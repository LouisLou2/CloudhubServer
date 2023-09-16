package com.cloudhub.normal.service.validator;

import com.cloudhub.normal.dao.FileDao;
import com.cloudhub.normal.dao.UserDao;
import com.cloudhub.normal.model.storage.MiniInfo;
import com.cloudhub.normal.utils.algorithm.StorageAlgo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service("FileOperateValidator")
public class FileOperateValidatorImpl implements FileOperateValidator{
    @Resource
    private FileDao fileDao;
    @Resource
    private UserDao userDao;
    /**
     * 检查用户空间是否足够
     * @param userId
     * @param requestSize
     * @return 返回-1表示空间不足，否则返回新的占用空间大小
     */
    public long SpaceEnoughCheck(long userId,long requestSize){
        long occupation=userDao.getUsedSpace(userId);
        long totalSpace=userDao.getTotalSpaceSize(userId);
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
    public long SpaceEnoughCheck(long userId, List<MiniInfo>list){
        long requestSize= StorageAlgo.getListSize(list);
        return SpaceEnoughCheck(userId,requestSize);
    }

    public boolean MoveCheck(long id,List<MiniInfo>list){
        return fileDao.isDescendantOfAny(id,list);
    }
    public boolean isFileExist(long id){
        return fileDao.isFileExist(id);
    }
}
