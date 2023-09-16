package com.cloudhub.normal.common.result;

import lombok.AllArgsConstructor;
import lombok.Getter;

public interface AppResultCode {
    @AllArgsConstructor
    @Getter
    enum SignUp{
        SUCCESS(100, "注册成功"),
        OCCUPATION_FAILURE(101, "用户名已被注册"),
        UNKNOWN_FAILURE(102, "未知错误");
        private final int code;
        private final String msg;
    }
    @AllArgsConstructor
    @Getter
    enum SignIn{
        SUCCESS(200, "登录成功"),
        FAILURE(201, "账号或密码错误"),
        //未注册
        NONEXISTENT(202, "账号不存在"),

        LOGGED_IN(203, "账号已登录"),

        UNKNOWN_FAILURE(204, "未知错误");
        private final int code;
        private final String msg;
    }
    @AllArgsConstructor
    @Getter
    enum SignOut{
        SUCCESS(300, "登出成功"),
        FAILURE(301, "登出失败"),
        UNKNOWN_FAILURE(302, "未知错误");
        private final int code;
        private final String msg;
    }
    /*下列字段用于获取或者修改（用户/文件/文件夹）的信息*/
    @AllArgsConstructor
    @Getter
    enum OPERATE{
        SUCCESS(400,"操作成功"),
        FAILURE(401,"操作失败"),
        NOT_LOGIN(402,"未登录,不允许此类操作"),
        UNKNOWN_FAILURE(402,"未知错误"),
        REQUEST_ERROR(403,"请求错误");
        private final int code;
        private final String msg;
    }
    @AllArgsConstructor
    @Getter
    enum CopyOrUpload{
        SUCCESS(500, "成功"),
        FAILURE(501, "操作被驳回"),
        CLOUD_NOT_ENOUGH(502, "网盘空间不足"),
        UNKNOWN_FAILURE(502, "未知错误");
        private final int code;
        private final String msg;
    }
    @AllArgsConstructor
    @Getter
    enum USER_UPDATE{
        SUCCESS(600,"操作成功"),
        FAILURE(601,"操作失败"),
        HAS_REGISTERED(602,"用户名已被注册"),
        UNKNOWN_FAILURE(603,"未知错误"),
        REQUEST_ERROR(604,"请求错误");
        private final int code;
        private final String msg;
    }
    @AllArgsConstructor
    @Getter
    enum FILE_FOLDER_UPDATE{
        SUCCESS(700,"操作成功"),
        FAILURE(701,"操作失败"),
        SAME_NAME(702,"在相同目录下不能创建同名文件/文件夹"),
        UNKNOWN_FAILURE(703,"未知错误"),
        REQUEST_ERROR(704,"请求错误");
        private final int code;
        private final String msg;
    }
    @AllArgsConstructor
    @Getter
    enum Load{

    }
}

