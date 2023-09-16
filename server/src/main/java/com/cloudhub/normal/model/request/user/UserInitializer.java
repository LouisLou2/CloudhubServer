package com.cloudhub.normal.model.request.user;

/**method Explanation.
* @author:Louis
* @Description: 注册请求
*/

public class UserInitializer {
    /**
     * 邮箱
     */
    private String email;

    /**
     * 密码
     */
    private String password;

    /**
     * 重复密码
     */
    private String repassword;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepassword() {
        return repassword;
    }

    public void setRepassword(String repassword) {
        this.repassword = repassword;
    }

    @Override
    public String toString() {
        return "UserInitializer["
                + " email=" + email + ","
                + " password=" + password + ","
                + " repassword=" + repassword
                + " ]";
    }
}
