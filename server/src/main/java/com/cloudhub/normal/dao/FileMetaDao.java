package com.cloudhub.normal.dao;


import com.cloudhub.normal.model.storage.FileMeta;

public interface FileMetaDao {
    FileMeta getFileMetaByHash(String hash);

    int decreaseCountByHash(String hash);

    int increaseCountByHash(String hash);

    boolean SameFileCheck(String hash);

    int addFileMeta(FileMeta fileMeta);

    int deleteFileMetaByHash(String hash);

    boolean sameFileCheck(String hash);
}
