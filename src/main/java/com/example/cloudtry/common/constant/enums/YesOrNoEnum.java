package com.example.cloudtry.common.constant.enums;

public enum YesOrNoEnum implements SuperEnum<Integer>{
    /**
     * no
     */
    NO(0, "no"),

    /**
     * yes
     */
    YES(1, "yes");

    final Integer code;
    final String msg;

    YesOrNoEnum(Integer code, String msg) {
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
}
