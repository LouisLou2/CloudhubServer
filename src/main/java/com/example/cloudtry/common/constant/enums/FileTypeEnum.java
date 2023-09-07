package com.example.cloudtry.common.constant.enums;

public enum FileTypeEnum implements SuperEnum<Integer>{

    IMAGE(1, "Picture"),
    AUDIO(2, "Audio"),
    VIDEO(3, "Video"),
    DOCUMENT(4, "Document"),
    Code(5, "Code"),
    OTHER(6, "Other");

    final Integer code;
    final String msg;

    FileTypeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public Integer code() {
        return code;
    }

    @Override
    public String value() {
        return msg;
    }

    @Override
    public boolean is(Integer code) {
        return SuperEnum.super.is(code);
    }

    @Override
    public boolean is(String code) {
        return SuperEnum.super.is(code);
    }
}
