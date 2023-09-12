package cn.keking.normal.dao.impl;

import cn.keking.normal.common.enums.BaseTypeEnum;
import cn.keking.normal.dao.IdMarkDao;
import org.springframework.stereotype.Repository;

@Repository("IdMarkDao")
public class IdMarkDaoImpl extends BaseDaoImpl implements IdMarkDao {
    @Override
    public void updateMaxId(BaseTypeEnum type, long id) {
        String sql = "update id_mark set max_id = ? where baseType = ?";
        JdbcTemplateObject.update(sql,id,type.getCode());
    }

    @Override
    public long getMaxId(BaseTypeEnum type) {
        String sql = "select max_id from id_mark where baseType = ?";
        return JdbcTemplateObject.queryForObject(sql,Long.class,type.getCode());
    }
}
