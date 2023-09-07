package com.example.cloudtry.common.constant.result;

public enum AppResultCode {
    /*响应成功*/
    SUCCESS(200),
    /*失败*/
    UNKNOWN_ERROR(10000),
    /*[网盘空间中]文件/文件夹 未找到*/
    NOT_FOUND(10001),
    /*[网盘空间中]文件/文件夹 删除失败*/
    DELETE_FAILURE(10002),
    /*[网盘空间中]剩余空间不足本次操作*/
    CLOUD_NOT_ENOUGH(10003),
    /*请求参数有误*/
    INVALID_PARAM_VALUE(10004),
    /*不支持的Http请求方法*/
    HTTP_REQUEST_METHOD_NOT_SUPPORTED(10005),

    //以下关于用户的错误码
    /*用户未登录*/
    USER_NOT_LOGIN(11000);

    private final int code;

    AppResultCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}

