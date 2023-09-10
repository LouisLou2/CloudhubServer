package com.example.cloudtry.common.result;

public enum Msg {
    /**
     * 成功
     */
    SUCCESS("操作成功"),

    /**
     * 很抱歉，您的操作失败了，建议您重试一下!
     */
    UNKNOWN_ERROR("很抱歉，您的操作失败了，建议您重试一下!"),

    /**
     * 参数错误
     */
    INVALID_PARAM_VALUE("参数错误"),

    /**
     * 请求 method 错误
     */
    HTTP_REQUEST_METHOD_NOT_SUPPORTED("请求方法错误"),

    /**
     * 数据删除失败
     */
    DELETE_FAILURE("数据删除失败"),

    /**
     * 数据未找到
     */
    NOT_FOUND("数据未找到"),

    /**
     * 网盘空间不足，无法完成操作！请增加磁盘存储。
     */
    CLOUD_NOT_ENOUGH("云端空间不足"),


    USER_NOT_LOGIN("未登录，不能执行该操作");
    private final String message;

    Msg(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
