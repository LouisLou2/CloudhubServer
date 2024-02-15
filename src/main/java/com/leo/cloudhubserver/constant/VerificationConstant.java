package com.leo.cloudhubserver.constant;

import java.util.Map;

public class VerificationConstant {
    //登录可用手段
    public static final Map<Byte,String> SIGNIN_TYPE = Map.of(
            (byte)0,"email",
            (byte)1,"phone"
    );
    public static String getType(byte type){
        return SIGNIN_TYPE.get(type);
    }
}
