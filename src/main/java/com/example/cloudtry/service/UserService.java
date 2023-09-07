package com.example.cloudtry.service;

import com.example.cloudtry.model.User;
import com.example.cloudtry.model.request.user.UserEnter;
import com.example.cloudtry.model.request.user.UserInitializer;

import java.util.List;

/**
 * 用户服务接口
 */
public interface UserService {

    /**
     * 初始化账户信息
     *
     * @param initializer 账户邮箱和密码（加密过的）
     */
    void initialize(UserInitializer initializer);

    /**
     * 用户登录
     *
     * @param enter 邮箱地址与密码
     * @return 会话 session id
     */
    String enter(UserEnter enter);

    /**
     * 更新用户账户空间
     *
     * @param keys 失效 redis key
     * @param user 更新的账户
     */
    void updateUserSpace(List<String> keys, User user);

    /**
     * 修改账户资料
     *
     * @param updater 要修改的资料
     * @param user    当前登录的账户
     * @return 修改后的账户资料
     */
    UserView updateUserInfo(UserUpdater updater, User user);

    /**
     * 修改账户密码
     *
     * @param updater 新密码
     * @param user    当前登录的用户
     * @return 修改后的账户资料
     */
    UserView updatePassword(PasswordUpdater updater, User user);

    void updateUserSpace(List<String> keys, User user);
}
