package com.leo.cloudhubserver.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value="user")
public class User {
    @TableId
    private int id;
    @TableField(value="username")
    private String username;
    @TableField(value="password")
    private String password;
    @TableField(value="email")
    private String email;
    @TableField(value="phone")
    private String phone;
    @TableField(value="capacity")
    private long capacity;
    @TableField(value="used")
    private long used;
    @TableField(value="lastLogin")
    private long lastLogin;
    @TableField(value="status")
    private byte status;
    @TableField(value="gender")
    private boolean gender;
}
