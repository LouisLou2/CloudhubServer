package com.example.cloudtry.model.request.user;

import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;

public class UserEnter {
    /**
     * 邮箱
     */
    @NotBlank
    private String email;

    /**
     * 密码
     */
    @NotBlank
    private String password;

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

    @Override
    public String toString() {
        return "UserEnter["
                + " email=" + email + ","
                + " password=" + password
                + " ]";
    }
}
