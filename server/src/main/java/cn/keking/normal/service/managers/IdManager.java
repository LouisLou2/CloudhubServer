package cn.keking.normal.service.managers;

import cn.hutool.core.util.IdUtil;
import cn.keking.normal.common.enums.BaseTypeEnum;
import cn.keking.normal.dao.BaseDao;

import java.util.concurrent.atomic.AtomicLong;

public class IdManager {
    private static IdManager instance;

    private AtomicLong fileId;
    private AtomicLong folderId;

    private AtomicLong userId;

    private IdManager() {
        // 私有构造函数，防止外部直接实例化
        fileId = new AtomicLong(BaseDao.MaxIdExisted(BaseTypeEnum.FILE));
        folderId =new AtomicLong (BaseDao.MaxIdExisted(BaseTypeEnum.FOLDER));
        userId = new AtomicLong(BaseDao.MaxIdExisted(BaseTypeEnum.USER));
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
        updateId(type);
        switch (type) {
            case FILE:
                return fileId.incrementAndGet();
            case FOLDER:
                return folderId.incrementAndGet();
            case USER:
                return userId.incrementAndGet();
            default:
                return -1;
        }
    }

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

    public void updateId(BaseTypeEnum type) {
        BaseDao.updateMaxId(type, generateId(type));
    }
}
