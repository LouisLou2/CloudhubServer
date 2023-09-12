package cn.keking.normal.model;

import cn.hutool.core.util.StrUtil;
import cn.keking.normal.common.constant.AppConstants;
import cn.keking.normal.utils.AvatarTools;
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
    private long id;
    /**
     * 用户名
     */
    private String name;
    /**
     * 密码
     */
    private String password;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 头像地址
     */
    private String avatar;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 账户总容量
     */
    private long totalSpaceSize;

    /**
     * 账户已用容量
     */
    private long usedSpace;
    /**
     * 性别
     */
    private int gender;
    /**
     * 年龄
     */
    private int age;
    /**
     * 生日
     */
    private long birthday;

    public static User initial() {
        User user = new User();
        user.setName(StrUtil.EMPTY);
        user.setEmail(StrUtil.EMPTY);
        user.setPassword(StrUtil.EMPTY);
        user.setAvatar(AvatarTools.getDefaultAvatarURL());
        user.setBirthday(0L);
        user.setAge(0);
        user.setGender(-1);
        user.setPhone(StrUtil.EMPTY);
        user.setTotalSpaceSize(AppConstants.Account.TOTAL_SPACE_SIZE);
        user.setUsedSpace(0L);
        return user;
    }
    public User(){
        super();
    }
    public static User initial(String userName, String phone,String password) {
        User user = initial();
        user.setName(userName);
        user.setPassword(password);
        user.setPhone(phone);
        return user;
    }

    public static User pack(long userId, long usedSpace) {
        User user = initial();
        user.setId(userId);
        user.setUsedSpace(usedSpace);
        return user;
    }

    @Override
    public String toString() {
        return "User["
                + " id=" + id + ","
                + " name=" + name + ","
                + " email=" + email + ","
                + " password=" + password + ","
                + " avatar=" + avatar + ","
                + " birthday=" + birthday + ","
                + " age=" + age + ","
                + " gender=" + gender + ","
                + " phone=" + phone + ","
                + " totalSpaceSize=" + totalSpaceSize + ","
                + " usedSpaceSize=" + usedSpace
                + " ]"
                + " "
                + super.toString();
    }
}
