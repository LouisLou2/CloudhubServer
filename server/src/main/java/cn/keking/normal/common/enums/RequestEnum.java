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
    LIST(14, "List"),
    UPDATE_USER_INFO(15, "Update user info");
    final int code;
    final String msg;
    public static RequestEnum getEnumByCode(int code){
        for(RequestEnum e:RequestEnum.values()){
            if(e.getCode()==code){
                return e;
            }
        }
        return null;
    }
    RequestEnum(int code){
        this.code=code;
        this.msg="";
    }
    @AllArgsConstructor
    @Getter
    public enum USER_INFO{
        NAME(1,"name"),
        PHONE(2,"phone"),
        PASSWORD(3,"password"),
        BIRTHDAY(4,"birthday");
        final int code;
        final String msg;
        public static USER_INFO getEnumByCode(int code){
            for(USER_INFO e:USER_INFO.values()){
                if(e.getCode()==code){
                    return e;
                }
            }
            return null;
        }
        USER_INFO(int code){
            this.code=code;
            this.msg="";
        }
    }
}
