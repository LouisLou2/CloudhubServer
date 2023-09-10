package com.example.cloudtry.dao;

import com.example.cloudtry.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserDao {
    public static boolean checkUserExist(String userName) {
        return false;
    }

    public static boolean matchUser(String userName, String password) {
        return true;
    }

    public static void insertUser(User user) {
        // 实现插入用户的逻辑
    }

    public static void updateSpaceSize(User user, Long spaceSize) {
        // 实现更新用户空间大小的逻辑
    }

    public static void updateUser(User user) {
        // 实现更新用户的逻辑
    }
    public static User queryUserByName(String userName) {
        return null;
    }
    public static User queryUserById(long id) {
        return null;
    }

    public static User queryUserByEmail(String email) {
        return null;
    }
}
