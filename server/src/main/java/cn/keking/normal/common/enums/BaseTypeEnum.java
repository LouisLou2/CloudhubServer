package cn.keking.normal.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum BaseTypeEnum{
    FOLDER(0,"folder"),
    FILE(1,"file"),

    USER(2,"user");
    final int code;
    final String msg;
    public static BaseTypeEnum getEnumByCode(int code){
        for(BaseTypeEnum e:BaseTypeEnum.values()){
            if(e.getCode()==code){
                return e;
            }
        }
        return null;
    }
    BaseTypeEnum(int code){
        this.code=code;
        this.msg="";
    }
}
