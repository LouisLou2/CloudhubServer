package cn.keking.normal.dao.impl;

import cn.keking.normal.dao.FileShareDao;
import org.springframework.stereotype.Repository;

@Repository("FileShareDao")
public class FileShareDaoImpl extends BaseDaoImpl implements FileShareDao{
    @Override
    public boolean sharedFileExist(long id) {
        String sql = "select count(*) from file_share where id = ?";
        int count = JdbcTemplateObject.queryForObject(sql,Integer.class,id);
        return count > 0;
    }
    @Override
    public boolean sharedFileMatch(long id, String psw) {
        String sql = "select count(*) from file_share where id = ? and shareCode = ?";
        int count = JdbcTemplateObject.queryForObject(sql,Integer.class,id,psw);
        return count > 0;
    }
}
