package com.example.cloudtry.service.impl;

import cn.hutool.core.util.ReUtil;
import com.example.cloudtry.common.AppProperties;
import com.example.cloudtry.common.constant.AppConstants;
import com.example.cloudtry.dao.UserDao;
import com.example.cloudtry.model.User;
import com.example.cloudtry.model.request.user.UserEnter;
import com.example.cloudtry.model.request.user.UserInitializer;
import com.example.cloudtry.service.FileService;
import com.example.cloudtry.service.UserService;
import com.example.cloudtry.utils.ExceptionTools;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource(name = "userDao")
    private UserDao userDao;
    @Resource(name = "fileService")
    private FileService fileService;
    @Override
    public void initialize(UserInitializer initializer) {
        // 检测是否已完成账户信息初始化
        if (AppProperties.ACCOUNT_HAS_INITIAL) {
            ExceptionTools.businessLogger("已完成账户初始化，请前往登录");
        }

        // 校验邮箱格式是否合法
        if (!ReUtil.isMatch(AppConstants.Regex.EMAIL_REGULAR, initializer.getEmail())) {
            ExceptionTools.businessLogger("邮箱不合法，请查证后重新输入");
        }
        //获得密码
        String password = initializer.getPassword();
        String rep = initializer.getRepassword();
        // 对比密码是否一致
        if (!password.equals(rep)) {
            ExceptionTools.businessLogger("两次输入不一致，请重新输入");
        }
        //// 完成对密码的 md5 加密
        //String password= SecureUtil.md5(initializer.getPassword());
        //
        //// 完成指定次数对重复密码的 md5 加密
        //String repassword=SecureUtil.md5(initializer.getRepassword());

        // 初始化用户账户信息
        User account = User.initial(initializer.getEmail(), password);
        userDao.insertUser(account);

        // 创建用户的文件根目录
        fileService.initialUserRoot(account.getId());

        // 修改是否已完成初始化的标志
        AppProperties.ACCOUNT_HAS_INITIAL = Boolean.TRUE;
    }

    @Override
    public String enter(UserEnter enter) {
        return null;
    }

    @Override
    public void updateUserSpace(List<String> keys, User user) {

    }

    @Override
    public UserView updateUserInfo(UserUpdater updater, User user) {
        return null;
    }

    @Override
    public UserView updatePassword(PasswordUpdater updater, User user) {
        return null;
    }
}
