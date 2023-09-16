package com.cloudhub.normal.dao.impl;

import com.cloudhub.normal.dao.IdMarkDao;
import org.springframework.stereotype.Repository;

@Repository("IdMarkDao")
public class IdMarkDaoImpl extends BaseDaoImpl implements IdMarkDao {
    @Override
    public void updateMaxId(int type, long id) {
        String sql = "update id_mark set max_id = ? where baseType = ?";
        JdbcTemplateObject.update(sql,id,type);
    }

    @Override
    public long getMaxId(int type) {
        String sql = "select max_id from id_mark where baseType = ?";
        return JdbcTemplateObject.queryForObject(sql,Long.class,type);
    }
}
