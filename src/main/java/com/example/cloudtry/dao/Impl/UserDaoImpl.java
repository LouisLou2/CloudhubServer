package com.example.cloudtry.dao.Impl;

import com.example.cloudtry.dao.UserDao;
import com.example.cloudtry.model.User;
import org.springframework.stereotype.Service;

@Service("userDao")
public class UserDaoImpl implements UserDao {
    @Override
    public void insertUser(User user) {

    }

    @Override
    public void updateSpaceSize(User user, Long spaceSize) {

    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public User queryUserById(String id) {
        return null;
    }

    @Override
    public User queryUserByEmail(String email) {
        return null;
    }
}
