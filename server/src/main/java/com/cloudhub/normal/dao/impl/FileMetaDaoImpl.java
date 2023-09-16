package com.cloudhub.normal.dao.impl;


import com.cloudhub.normal.dao.FileMetaDao;
import com.cloudhub.normal.model.storage.FileMeta;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

@Repository("FileMetaDao")
public class FileMetaDaoImpl extends BaseDaoImpl implements FileMetaDao {
    @Override
    public FileMeta getFileMetaByHash(String hash) {
        String sql = "select * from file_meta where hash = ?";
        FileMeta fileMeta = JdbcTemplateObject.queryForObject(sql, new BeanPropertyRowMapper<>(FileMeta.class), hash);
        return fileMeta;
    }


    @Override
    public int decreaseCountByHash(String hash) {
            String sql = "update file_meta set count = count-1 where hash = ?";
            JdbcTemplateObject.update(sql, hash);
            String countSql = "select count from file_meta where hash = ?";
            int count = JdbcTemplateObject.queryForObject(countSql,int.class,hash);
            if(count==0){
                String deleteSql = "delete from file_meta where hash = ?";
                int deleteCount = JdbcTemplateObject.update(deleteSql, hash);
                System.out.println("最后一份文件的删除");
            }
            return count;
    }

    @Override
    public int increaseCountByHash(String hash) {
        if(SameFileCheck(hash)){
            String sql = "update file_meta set count = count+1 where fileId = ?";
            int count = JdbcTemplateObject.update(sql, hash);
            return count;
        }
        return 0;
    }

    @Override
    public boolean SameFileCheck(String hash) {
        String sql = "select count(*) from file_meta where hash = ?";
        Integer count = JdbcTemplateObject.queryForObject(sql, int.class, hash);
        return count>0;
    }

    @Override
    public int addFileMeta(FileMeta fileMeta) {
        String sql = "insert into file_meta(hash,bucketName,objectKey,count) values(?,?,?,?,?)";
        JdbcTemplateObject.update(sql,
                fileMeta.getHash(),fileMeta.getBucketName(),fileMeta.getObjectKey(),1);
        return 1;
    }

    @Override
    public int deleteFileMetaByHash(String hash) {
        String deleteSql = "delete from file_meta where hash = ?";
        int deleteCount = JdbcTemplateObject.update(deleteSql, hash);
        return deleteCount;
    }

    @Override
    public boolean sameFileCheck(String hash) {
        String sql = "select count(*) from file_meta where hash = ?";
        Integer count = JdbcTemplateObject.queryForObject(sql, int.class, hash);
        return count>0;
    }

}
