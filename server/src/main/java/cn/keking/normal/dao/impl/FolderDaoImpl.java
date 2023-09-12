package cn.keking.normal.dao.impl;

import cn.keking.normal.common.enums.BaseTypeEnum;
import cn.keking.normal.dao.FolderDao;
import cn.keking.normal.model.storage.FolderInfo;
import cn.keking.normal.model.storage.MiniInfo;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository("FolderDao")
public class FolderDaoImpl extends BaseDaoImpl implements FolderDao {
    @Override
    public long getPidByFolderId(long id) {
        String sql = "select pid from folder where id = ?";
        return JdbcTemplateObject.queryForObject(sql,Long.class,id);
    }
//    @Override
//    public List<Folder> getTopFolderByUserId(int userId) {
//        String sql = "select * from folder where userId = ? and parentFolderId is null ";
//        List<Folder> topFolders = JdbcTemplateObject.query(sql, new BeanPropertyRowMapper<>(Folder.class), userId);
//        return topFolders;
//    }

    @Override
    public List<FolderInfo> getRootFolder(String userName) {
        String sql = "select * from folder where userName = ? and pid =0";
        List<FolderInfo> topFolders = JdbcTemplateObject.query(sql, new BeanPropertyRowMapper<>(FolderInfo.class), userName);
        return topFolders;
    }

    @Override
    public List<FolderInfo> getChildrenFolder(long id) {
        String sql = "select * from folder where pid = ?";
        List<FolderInfo> childrenFolders = JdbcTemplateObject.query(sql, new BeanPropertyRowMapper<>(FolderInfo.class), id);
        return childrenFolders;
    }

    @Override
    public void creatNewFolder(FolderInfo folder) {
        String sql = "insert into folder(id,userId,name,timeStamp,pid) values(?,?,?,?)";
        JdbcTemplateObject.update(sql,folder.getId(),folder.getUserId(),folder.getName(),folder.getTimeStamp(),folder.getPid());

    }
    @Override
    public void createNewFolder(long id, String name,long pid,long timeStamp){
        //先获取pid的userId
        String sql = "select userId from folder where id = ?";
        int userId = JdbcTemplateObject.queryForObject(sql,Integer.class,pid);
        sql = "insert into folder(id,userId,name,timeStamp,pid,) values(?,?,?,?)";
        JdbcTemplateObject.update(sql,id,userId,name,timeStamp,pid);
    }
    @Override
    public void deleteFolderByFolderId(long id) {
        String sql ="delete from folder where id = ?";
        int count = JdbcTemplateObject.update(sql,id);
    }

    @Override
    public boolean checkFolderExist(long pid,String name) {
        String sql = "SELECT COUNT(*) FROM folder WHERE pid = ? AND name = ?";
        try {
            int count = JdbcTemplateObject.queryForObject(sql, Integer.class, pid, name);
            return count > 0;
        } catch (Exception e) {
            // 处理异常
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void updateFolderName(long id,String name) {
        String sql = "update folder set name = ? where id = ?";
        try {
            JdbcTemplateObject.update(sql,name,id);
        } catch (Exception e) {
            // 处理异常
            e.printStackTrace();
        }
    }
    @Override
    public void changeFolderParent(long id, long newPid){
        String sql = "update folder set pid = ? where id = ?";
        JdbcTemplateObject.update(sql,newPid,id);
    }

    @Override
    public boolean isChildOf(long id, long pid){
        String sql = "select count(*) from folder where id = ? and pid = ?";
        int count = JdbcTemplateObject.queryForObject(sql,Integer.class,id,pid);
        return count > 0;
    }
    @Override
    public boolean isDescendantOf(long id, long pid){
        long apid=id;
        do{
            apid=getPidByFolderId(apid);
        }while(apid!=0L&&apid!=pid);
        return apid==pid;
    }
    //FolderDaoImpl的isDescendantOfAny方法与FolderDao的isDescendantOfAny方法的思路一样，形式稍有不同
    @Override
    public boolean isDescendantOfAny(long id, long[] pids){
        String sql = "select count(*) from folder where id = ? and pid = ?";
        for (long pid : pids) {
            int count = JdbcTemplateObject.queryForObject(sql,Integer.class,id,pid);
            if(count > 0){
                return true;
            }
        }
        return false;
    }
    @Override
    public boolean isDescendantOfAny(long id, List<MiniInfo>list){
        String sql = "select count(*) from folder where id = ? and pid = ?";
        for (MiniInfo info : list) {
            if(info.getBaseType()== BaseTypeEnum.FILE)continue;
            int count = JdbcTemplateObject.queryForObject(sql,Integer.class,id,info.getId());
            if(count > 0){
                return true;
            }
        }
        return false;
    }
}
