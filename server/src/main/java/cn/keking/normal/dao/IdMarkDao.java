package cn.keking.normal.dao;

import cn.keking.normal.common.enums.BaseTypeEnum;

public interface IdMarkDao {
    void updateMaxId(BaseTypeEnum type, long id);
    long getMaxId(BaseTypeEnum type);
}
