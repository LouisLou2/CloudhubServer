package com.leo.cloudhubserver.constant;

public enum ResponseEnum {
    SUCCESS(0, "success"),
    FAIL(1, "fail"),
    ERROR(2, "error");

    private final int code;
    private final String message;

    ResponseEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
