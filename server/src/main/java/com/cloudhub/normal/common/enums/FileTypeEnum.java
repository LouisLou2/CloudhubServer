package com.cloudhub.normal.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
/*
IMAGE(1, "Picture"),
    AUDIO(2, "Audio"),
    VIDEO(3, "Video"),
    DOCUMENT(4, "Document"),
    OTHER(5, "Other");
* */
@AllArgsConstructor
@Getter
public class FileTypeEnum{
    //用public static final int定义常量
    public static final int IMAGE=1;
    public static final int AUDIO=2;
    public static final int VIDEO=3;
    public static final int DOCUMENT=4;
    public static final int OTHER=5;
}
