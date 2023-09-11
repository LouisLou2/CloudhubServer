package cn.keking.normal.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RequestEnum {
    SIGN_UP(1, "Sign up"),
    SIGN_IN(2, "Sign in"),
    SIGN_OUT(3, "Sign out"),
    UPLOAD(4, "Upload"),
    DOWNLOAD(5, "Download"),
    DELETE(6, "Delete"),
    CREATE_FOLDER(7, "Create folder"),
    RENAME(8, "Rename"),
    MOVE(9, "Move"),
    COPY(10, "Copy"),
    PREVIEW(11, "Preview"),
    SHARE(12, "Share"),
    CANCEL_SHARE(13, "Cancel share"),
    LIST(14, "List");
    final Integer code;
    final String msg;
}
