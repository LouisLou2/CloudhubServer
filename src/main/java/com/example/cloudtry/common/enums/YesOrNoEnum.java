package com.example.cloudtry.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum YesOrNoEnum{
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
}
