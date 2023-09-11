package cn.keking.normal.common.result;

public enum ResultInfo {
    SUCCESS(200, Msg.SUCCESS),
    UNKNOWN_ERROR(10000, Msg.UNKNOWN_ERROR),
    NOT_FOUND(10001, Msg.NOT_FOUND),
    DELETE_FAILURE(10002, Msg.DELETE_FAILURE),
    CLOUD_NOT_ENOUGH(10003, Msg.CLOUD_NOT_ENOUGH),
    INVALID_PARAM_VALUE(10004, Msg.INVALID_PARAM_VALUE),
    HTTP_REQUEST_METHOD_NOT_SUPPORTED(10005, Msg.HTTP_REQUEST_METHOD_NOT_SUPPORTED),
    //以下关于用户的错误码
    //用户未登录
    USER_NOT_LOGIN(11000, Msg.USER_NOT_LOGIN);
    private final int code;
    private final Msg message;

    ResultInfo(int code, Msg message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message.getMessage();
    }
}
