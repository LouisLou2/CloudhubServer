package com.cloudhub.normal.dao;

public interface FileShareDao {
    boolean sharedFileExist(long id);
    boolean sharedFileMatch(long id, String psw);
}
