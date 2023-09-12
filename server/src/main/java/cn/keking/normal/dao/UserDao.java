package cn.keking.normal.dao;


import cn.keking.normal.model.User;

public interface UserDao {
    void updateInfo(String key,long id,String value);
    boolean matchUser(String name,String password);
    long getUserIdByName(String name);
    String getPasswordByName(String name);
    String getPasswordById(long id);
    User getUserById(long id);
    boolean checkUserExist(String name);
    boolean checkUserExist(long id);
    void addUser(User user);
    User getUserInfoByUserName(String name);
    void updateUserName(long id,String newName);
    void updatePassword(long id,String password);
    long getTotalSpaceSize(long id);
    void updateTotalSpaceSize(long id,long totalSpaceSize);
    long getUsedSpace(long id);
    void updateUsedSpace(long id,long usedSpace);
}
