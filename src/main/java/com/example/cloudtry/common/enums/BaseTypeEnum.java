package com.example.cloudtry.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum BaseTypeEnum{
    FOLDER(0,"folder"),
    FILE(1,"file"),

    USER(2,"user");
    final Integer code;
    final String msg;

    BaseTypeEnum(int code, String msg) {
        this.code = (Integer) code;
        this.msg = msg;
    }
}
