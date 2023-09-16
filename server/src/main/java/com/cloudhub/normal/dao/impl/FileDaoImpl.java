package com.cloudhub.normal.dao.impl;


import com.cloudhub.normal.common.enums.BaseTypeEnum;
import com.cloudhub.normal.dao.FileDao;
import com.cloudhub.normal.model.BucketAndOb;
import com.cloudhub.normal.model.storage.FileInfo;
import com.cloudhub.normal.model.storage.MiniInfo;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("FileDao")
public class FileDaoImpl extends BaseDaoImpl implements FileDao {
    @Override
    public boolean isFileExist(long id) {
        String sql = "select count(*) from file where id = ?";
        int count = JdbcTemplateObject.queryForObject(sql,Integer.class,id);
        return count > 0;
    }
    @Override
    public boolean isSameNameExistInFolder(long pid,String name) {
        String sql = "select count(*) from file where pid = ? and name = ?";
        int count = JdbcTemplateObject.queryForObject(sql,Integer.class,pid,name);
        return count > 0;
    }
    @Override
    public long getSizeByFileId(long id) {
        String sql = "select size from file where id = ?";
        return JdbcTemplateObject.queryForObject(sql,Long.class,id);
    }
    @Override
    public List<MiniInfo> getMiniChildList(long pid) {
        String sql = "select id,fileType from file where pid = ?";
        return JdbcTemplateObject.query(sql,new BeanPropertyRowMapper<>(MiniInfo.class),pid);
    }
    @Override
    public List<FileInfo> getFileInfoByFolderId(long pid) {
        String sql = "select * from file where pid = ?";
        return JdbcTemplateObject.query(sql,new BeanPropertyRowMapper<>(FileInfo.class),pid);
    }

    @Override
    public void addFile(FileInfo file) {
        String sql = "INSERT INTO File (id,userId,name,hash, size, fileType,createTime, pid) " +
                "VALUES (?,?, ?, ?, ?, ?, ?)";
       int count = JdbcTemplateObject.update(
                sql,
                file.getId(),
                file.getUserId(),
                file.getName(),
                file.getHash(),
                file.getSize(),
                file.getType(),
                file.getTimeStamp(),
                file.getPid()
        );
        System.out.println("新增："+count);
    }
    @Override
    public void ReceiveFileById(long userId,long originFileId,long ownFileId) {
        //先得到原文件的信息
        String sql = "select * from file where id = ?";
        FileInfo fileInfo = JdbcTemplateObject.queryForObject(sql,new BeanPropertyRowMapper<>(FileInfo.class),originFileId);
        //更改info的userId和id
        fileInfo.setUserId(userId);
        fileInfo.setId(ownFileId);
        //插入数据库
        addFile(fileInfo);
    }
    @Override
    public void deleteFile(long id){
        String sql ="delete from file where id = ?";
        int count = JdbcTemplateObject.update(sql,id);
        System.out.println("删除"+count);
    }

    @Override
    public void changeParent(List<MiniInfo> childs, long newPid) {
        String sql = "update file set pid = ? where id = ?";
        for (MiniInfo child : childs) {
            JdbcTemplateObject.update(sql,newPid,child.getId());
        }
    }

    @Override
    public FileInfo getFileInfoById(long id) {
        String sql = "select * from file where id = ?";
        return JdbcTemplateObject.queryForObject(sql,new BeanPropertyRowMapper<>(FileInfo.class),id);
    }

    @Override
    public List<FileInfo> getRootFileList(String userName) {
        //根据用户名获取用户id
        String sql = "select id from user where name = ?";
        long userId = JdbcTemplateObject.queryForObject(sql,Long.class,userName);
        //根据用户id获取用户根目录下的文件
        String sql2 = "SELECT * FROM file WHERE userId = ? AND pid =0";
        List<FileInfo>list=JdbcTemplateObject.query(sql2, new BeanPropertyRowMapper<>(FileInfo.class), userId);
        return list;

        //设置一个用于测试的List<FileInfo>
        //List<FileInfo> list=new ArrayList<>();
        //向list中添加元素
        //list.add(new FileInfo(1L,1L,"test1","test1",1L,1,1L,1L));
        //list.add(new FileInfo(2L,2L,"test2","test2",2L,2,2L,2L));
        //list.add(new FileInfo(3L,3L,"test3","test3",3L,3,3L,3L));
        //return list;
    }

    @Override
    public List<FileInfo> getFileInfoListByFolderId(long id) {
        String sql = "SELECT * FROM file WHERE pid = ?";
        return JdbcTemplateObject.query(sql, new BeanPropertyRowMapper<>(FileInfo.class), id);
    }

    @Override
    public void updateFileName(long id,String newName) {
        String sql = "update File set name = ? where id =?";
        try {
            JdbcTemplateObject.update(sql,newName,id);
        } catch (Exception e) {
            // 处理异常
            e.printStackTrace();
        }
    }

    @Override
    public BucketAndOb getBucketNameAndObjectKey(long id) {
        //先找hash值
        String sql = "select hash from file where id = ?";
        String hash = JdbcTemplateObject.queryForObject(sql,String.class,id);
        //再找bucketName和objectKey
        //分开来写
        String sql2 = "select bucketName from file_meta where hash = ?";
        String bucketName = JdbcTemplateObject.queryForObject(sql2,String.class,hash);
        String sql3 = "select objectKey from file_meta where hash = ?";
        String objectKey = JdbcTemplateObject.queryForObject(sql3,String.class,hash);
        return new BucketAndOb(bucketName,objectKey);
        //String sql2 = "select bucketName,objectKey from file_meta where hash = ?";
        //return JdbcTemplateObject.queryForObject(sql2, BucketAndOb.class,hash);
    }

    @Override
    public String getHashByFileId(long id) {
        String sql = "select hash from file where id = ?";
        String hash = JdbcTemplateObject.queryForObject(sql,String.class,id);
        return hash;
    }
    @Override
    public void changeFileParent(long id, long newPid) {
        String sql = "update file set pid = ? where id = ?";
        JdbcTemplateObject.update(sql,newPid,id);
    }
    @Override
    public long getPidByFileId(long id) {
        String sql = "select pid from file where id = ?";
        return JdbcTemplateObject.queryForObject(sql,Long.class,id);
    }
    @Override
    public boolean isChildOf(long id, long pid) {
        String sql = "select count(*) from file where id = ? and pid = ?";
        int count = JdbcTemplateObject.queryForObject(sql,Integer.class,id,pid);
        return count > 0;
    }
    @Override
    public boolean isDescendantOf(long id, long pid) {
        long apid=id;
        do{
            apid=getPidByFileId(apid);
        }while(apid!=0L&&apid!=pid);
        return apid==pid;
    }
    @Override
    public boolean isDescendantOfAny(long id, long[] pids) {
        for(long pid:pids){
            if(isDescendantOf(id,pid)){
                return true;
            }
        }
        return false;
    }
    @Override
    public boolean isDescendantOfAny(long id, List<MiniInfo> list) {
        for(MiniInfo info:list){
            if(info.getBaseType()== BaseTypeEnum.FOLDER&&isDescendantOf(id,info.getId())){
                return true;
            }
        }
        return false;
    }
}
