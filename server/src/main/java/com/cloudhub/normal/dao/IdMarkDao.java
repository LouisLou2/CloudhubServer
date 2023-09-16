package com.cloudhub.normal.dao;

public interface IdMarkDao {
    void updateMaxId(int type, long id);
    long getMaxId(int type);
}
