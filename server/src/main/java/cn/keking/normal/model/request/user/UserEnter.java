package cn.keking.normal.model.request.user;

public class UserEnter {
    /**
     * 邮箱
     */
    private String id;

    /**
     * 密码
     */
    private String password;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserEnter["
                + " id=" + id + ","
                + " password=" + password
                + " ]";
    }
}
