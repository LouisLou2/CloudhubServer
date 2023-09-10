package com.example.cloudtry.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum FileTypeEnum{

    IMAGE(1, "Picture"),
    AUDIO(2, "Audio"),
    VIDEO(3, "Video"),
    DOCUMENT(4, "Document"),
    OTHER(5, "Other");

    final Integer code;
    final String msg;
}
