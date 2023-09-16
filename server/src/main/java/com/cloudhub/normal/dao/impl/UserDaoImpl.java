package com.cloudhub.normal.dao.impl;


import com.cloudhub.normal.dao.UserDao;
import com.cloudhub.normal.model.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;


@Repository("UserDao")
public class UserDaoImpl extends BaseDaoImpl implements UserDao {
    @Override
    public void updateInfo(String key,long id,String value) {
        String sql = "update user set "+key+" = ? where id=?";
        if(key.equals("phone")||key.equals("birthday")){
            long valueLong = Long.parseLong(value);
            JdbcTemplateObject.update(sql,valueLong,id);
        }
        try {
            JdbcTemplateObject.update(sql,value,id);
        } catch (Exception e) {
            // 处理异常
            e.printStackTrace();
        }
    }
    @Override
    public boolean matchUser(String name, String password) {
        String sql = "select count(*) from user where name = ? and password = ?";
        try {
            int count = JdbcTemplateObject.queryForObject(sql, Integer.class, name, password);
            if(count > 1){
                System.out.println("警告，重复的用户名和密码");
            }
            return count == 1;
        } catch (Exception e) {
            // 处理异常
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public long getUserIdByName(String name) {
        String sql = "select id from user where name = ?";
        try {
            return JdbcTemplateObject.queryForObject(sql,Long.class,name);
        } catch (Exception e) {
            // 处理异常
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public String getPasswordByName(String name) {
        String sql = "select password from user where name = ?";

        try {
//            return JdbcTemplateObject.queryForObject(sql, new Object[]{name}, String.class);
            return JdbcTemplateObject.queryForObject(sql,String.class,name);
        } catch (Exception e) {
            // 处理异常，例如用户不存在的情况
            return null;
        }

    }

    @Override
    public String getPasswordById(long id) {
        String sql = "select password from user where id = ?";

        try {
//            return JdbcTemplateObject.queryForObject(sql, new Object[]{name}, String.class);
            return JdbcTemplateObject.queryForObject(sql,String.class,id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public User getUserById(long id) {
        String sql = "SELECT * FROM user WHERE id = ?";
        System.out.println(JdbcTemplateObject);
        try {
            return JdbcTemplateObject.queryForObject(sql, new BeanPropertyRowMapper<>(User.class),id);
        } catch (Exception e) {
            // 处理异常，例如用户不存在的情况
            e.printStackTrace();
            return null;
        }
    }

    //根据用户名检测用户是否存在
    @Override
    public boolean checkUserExist(String name) {
        String sql = "SELECT COUNT(*) FROM user WHERE name = ?";
        try {
            int count = JdbcTemplateObject.queryForObject(sql, Integer.class, name);
            return count > 0;
        } catch (Exception e) {
            // 处理异常
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public boolean checkUserExist(long id) {
        String sql = "SELECT COUNT(*) FROM user WHERE id = ?";
        try {
            int count = JdbcTemplateObject.queryForObject(sql, Integer.class, id);
            return count > 0;
        } catch (Exception e) {
            // 处理异常
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public void addUser(User user) {
        String sql = "INSERT INTO user (id,name,password,phone,avatar,email,totalSpaceSize,usedSpace,gender,age,birthday) VALUES (?, ?, ?,?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            JdbcTemplateObject.update(sql, user.getId(),user.getName(),user.getPassword(),user.getPhone(),user.getAvatar(),user.getEmail(),user.getTotalSpace(),user.getUsedSpace(),user.getGender(),user.getAge(),user.getBirthday());
        } catch (Exception e) {
            // 处理异常
            e.printStackTrace();
        }
    }


    @Override
    public User getUserByName(String name) {
        String sql = "SELECT * FROM user WHERE name = ?";
        try {
            return JdbcTemplateObject.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), name);
        } catch (Exception e) {
            // 处理异常，例如用户不存在的情况
            e.printStackTrace();
            return null;
        }
        //User user=new User(100L,"joe","123",123,"avatar","@qq",123,123,1,1,123);
        //return user;
    }

    @Override
    public void updateUserName(long id, String newName) {
        String sql = "update User set name = ? where id=?";
        try {
            JdbcTemplateObject.update(sql,newName,id);
        } catch (Exception e) {
            // 处理异常
            e.printStackTrace();
        }
    }

    @Override
    public void updatePassword(long id, String password) {
        String sql = "update User set password = ? where id=?";
        try {
            JdbcTemplateObject.update(sql,password,id);
        } catch (Exception e) {
            // 处理异常
            e.printStackTrace();
        }
    }
    //@Override
    //public void updateAvatar(long userId, String avatar) {
    //    String sql = "update User set avatar = ? where userId=?";
    //    try {
    //        JdbcTemplateObject.update(sql,avatar,userId);
    //    } catch (Exception e) {
    //        // 处理异常
    //        e.printStackTrace();
    //    }
    //}
    @Override
    public long getTotalSpaceSize(long id) {
        String sql = "select totalSpaceSize from user where id = ?";
        try {
            return JdbcTemplateObject.queryForObject(sql,Long.class,id);
        } catch (Exception e) {
            // 处理异常
            e.printStackTrace();
            return 0;
        }
    }
    @Override
    public void updateTotalSpaceSize(long id,long totalSpaceSize) {
        String sql = "update user set totalSpace = ? where id=?";
        try {
            JdbcTemplateObject.update(sql,totalSpaceSize,id);
        } catch (Exception e) {
            // 处理异常
            e.printStackTrace();
        }
    }
    @Override
    public long getUsedSpace(long id) {
        String sql = "select usedSpace from user where id = ?";
        try {
            return JdbcTemplateObject.queryForObject(sql,Long.class,id);
        } catch (Exception e) {
            // 处理异常
            e.printStackTrace();
            return 0;
        }
    }
    @Override
    public void updateUsedSpace(long id,long usedSpace) {
        String sql = "update user set usedSpace = ? where id=?";
        try {
            JdbcTemplateObject.update(sql,usedSpace,id);
        } catch (Exception e) {
            // 处理异常
            e.printStackTrace();
        }
    }
}
