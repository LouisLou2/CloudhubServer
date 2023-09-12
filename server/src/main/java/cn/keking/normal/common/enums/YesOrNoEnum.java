package cn.keking.normal.common.enums;

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
    public static YesOrNoEnum getEnumByCode(int code){
        for(YesOrNoEnum e:YesOrNoEnum.values()){
            if(e.getCode()==code){
                return e;
            }
        }
        return null;
    }
    YesOrNoEnum(int code){
        this.code=code;
        this.msg="";
    }
}
