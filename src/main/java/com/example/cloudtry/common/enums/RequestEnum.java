package com.example.cloudtry.common.enums;

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
    SHARE(11, "Share"),
    CANCEL_SHARE(12, "Cancel share"),
    LIST(13, "List"),
    LIST_SHARED(14, "List shared"),
    LIST_TRASH(15, "List trash"),
    RECOVER(16, "Recover"),
    EMPTY_TRASH(17, "Empty trash"),
    SEARCH(18, "Search"),
    LIST_VERSION(19, "List version"),
    RECOVER_VERSION(20, "Recover version"),
    DELETE_VERSION(21, "Delete version"),
    LIST_RECYCLE_BIN(22, "List recycle bin"),
    LIST_RECYCLE_BIN_VERSION(23, "List recycle bin version"),
    RECOVER_RECYCLE_BIN(24, "Recover recycle bin"),
    RECOVER_RECYCLE_BIN_VERSION(25, "Recover recycle bin version"),
    DELETE_RECYCLE_BIN(26, "Delete recycle bin"),
    DELETE_RECYCLE_BIN_VERSION(27, "Delete recycle bin version"),
    LIST_SHARE(28, "List share"),
    LIST_SHARE_VERSION(29, "List share version"),
    RECOVER_SHARE(30, "Recover share"),
    RECOVER_SHARE_VERSION(31, "Recover share version"),
    DELETE_SHARE(32, "Delete share"),
    DELETE_SHARE_VERSION(33, "Delete share version"),
    LIST_SHARE_RECYCLE_BIN(34, "List share recycle bin"),
    LIST_SHARE_RECYCLE_BIN_VERSION(35, "List share recycle bin version"),
    RECOVER_SHARE_RECYCLE_BIN(36, "Recover share recycle bin"),
    RECOVER_SHARE_RECYCLE_BIN_VERSION(37, "Recover share recycle bin version"),
    DELETE_SHARE_RECYCLE_BIN(38, "Delete share recycle bin"),
    DELETE_SHARE_RECYCLE_BIN_VERSION(39, "Delete share recycle bin version"),
    LIST_SHARE_TRASH(40, "List share trash"),
    LIST_SHARE_TRASH_VERSION(41, "List share trash version"),
    RECOVER_SHARE_TRASH(42, "Recover share trash"),
    RECOVER_SHARE_TRASH_VERSION(43, "Recover share trash version"),
    DELETE_SHARE_TRASH(44, "Delete share trash"),
    DELETE_SHARE_TRASH_VERSION(45, "Delete share trash version"),
    LIST_TRASH_VERSION(46, "List trash version");
    final Integer code;
    final String msg;
}
