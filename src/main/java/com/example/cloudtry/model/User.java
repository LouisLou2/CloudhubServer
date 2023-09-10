package com.example.cloudtry.model;

import cn.hutool.core.util.StrUtil;
import com.example.cloudtry.common.constant.AppConstants;
import com.example.cloudtry.utils.AvatarTools;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * 用户模型
 */
@AllArgsConstructor
@Setter
@Getter
public class User extends BaseParameter {

    /**
     * 主键
     */
    private String id;

    /**
     * 昵称
     */
    private String userName;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 密码
     */
    private String password;

    /**
     * 头像地址
     */
    private String avatar;

    /**
     * 生日
     */
    private Long birthday;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 性别
     */
    private Integer gender;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 账户总容量
     */
    private Long totalSpaceSize;

    /**
     * 账户已用容量
     */
    private Long usedSpaceSize;

    public static User initial() {
        User user = new User();
        user.setUserName(StrUtil.EMPTY);
        user.setEmail(StrUtil.EMPTY);
        user.setPassword(StrUtil.EMPTY);
        user.setAvatar(AvatarTools.getDefaultAvatarURL());
        user.setBirthday(0L);
        user.setAge(0);
        user.setGender(-1);
        user.setPhone(StrUtil.EMPTY);
        user.setTotalSpaceSize(AppConstants.Account.TOTAL_SPACE_SIZE);
        user.setUsedSpaceSize(0L);
        return user;
    }
    public User(){
        super();
    }
    public static User initial(String userName, String phone,String password) {
        User user = initial();
        user.setUserName(userName);
        user.setPassword(password);
        user.setPhone(phone);
        return user;
    }

    public static User pack(String userId, Long usedSpaceSize) {
        User user = initial();
        user.setId(userId);
        user.setUsedSpaceSize(usedSpaceSize);
        return user;
    }

    @Override
    public String toString() {
        return "User["
                + " id=" + id + ","
                + " userName=" + userName + ","
                + " email=" + email + ","
                + " password=" + password + ","
                + " avatar=" + avatar + ","
                + " birthday=" + birthday + ","
                + " age=" + age + ","
                + " gender=" + gender + ","
                + " phone=" + phone + ","
                + " totalSpaceSize=" + totalSpaceSize + ","
                + " usedSpaceSize=" + usedSpaceSize
                + " ]"
                + " "
                + super.toString();
    }
}
