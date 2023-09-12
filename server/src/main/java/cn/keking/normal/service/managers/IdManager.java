package cn.keking.normal.service.managers;

import cn.hutool.core.util.IdUtil;
import cn.keking.normal.common.enums.BaseTypeEnum;
import cn.keking.normal.dao.IdMarkDao;

import javax.annotation.Resource;
import java.util.concurrent.atomic.AtomicLong;

public class IdManager {
    @Resource
    private IdMarkDao idMarkDao;
    private static IdManager instance;

    private AtomicLong fileId;
    private AtomicLong folderId;

    private AtomicLong userId;

    private IdManager() {
        // 私有构造函数，防止外部直接实例化
        fileId = new AtomicLong(idMarkDao.getMaxId(BaseTypeEnum.FILE));
        folderId =new AtomicLong (idMarkDao.getMaxId(BaseTypeEnum.FOLDER));
        userId = new AtomicLong(idMarkDao.getMaxId(BaseTypeEnum.USER));
    }

    // 公共静态方法，用于获取单例实例
    public static IdManager getInstance() {
        if (instance == null) {
            instance = new IdManager();
        }
        return instance;
    }

    public String fastSimpleUuid() {
        return IdUtil.fastSimpleUUID();
    }

    public long generateIdAndUpdate(BaseTypeEnum type) {
        long newId = switch (type) {
            case FILE -> fileId.incrementAndGet();
            case FOLDER -> folderId.incrementAndGet();
            case USER -> userId.incrementAndGet();
            default -> -1;
        };
        idMarkDao.updateMaxId(type, newId);
        return newId;
    }
    //注意，这个方法不会更新数据库，只会返回期望的新id
    public long generateId(BaseTypeEnum type) {
        switch (type) {
            case FILE:
                return fileId.get()+1;
            case FOLDER:
                return folderId.get()+1;
            case USER:
                return userId.get()+1;
            default:
                return -1;
        }
    }

    //public void updateId(BaseTypeEnum type) {
    //    BaseDao.updateMaxId(type, generateId(type));
    //}
}
