package com.example.cloudtry.model.request.user;

import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;

public class UserEnter {
    /**
     * 邮箱
     */
    @NotBlank
    private String id;

    /**
     * 密码
     */
    @NotBlank
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
