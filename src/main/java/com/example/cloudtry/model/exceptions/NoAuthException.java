package com.example.cloudtry.model.exceptions;
import com.example.cloudtry.common.constant.result.ResultInfo;

/**
 * 权限不足异常
 */
public class NoAuthException extends RuntimeException {

    /**
     * code 码
     */
    private Integer code;

    /**
     * 说明描述
     */
    private String msg;

    public NoAuthException() {
        super();
        code = ResultInfo.USER_NOT_LOGIN.getCode();
        msg = ResultInfo.USER_NOT_LOGIN.getMessage();
    }

    public NoAuthException(ResultInfo result) {
        super();
        code = result.getCode();
        msg = result.getMessage();
    }

    public NoAuthException(String message) {
        super(message);
    }

    public NoAuthException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoAuthException(Throwable cause) {
        super(cause);
    }

    public NoAuthException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public Integer code() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String value() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "NoAuthException["
                + " code=" + code + ","
                + " msg=" + msg
                + " ]"
                + " "
                + super.toString();
    }
}
