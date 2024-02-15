package com.leo.cloudhubserver.constant;

import java.util.Map;

public class UserStatusEnum {
    public static final byte NORMAL = 0;
    public static final byte LOCKED = 1;
    public static final byte UNACTIVATED = 2;
    public static final Map<Byte,String> STATUSMAP = Map.of(
            (byte)0,"normal",
            (byte)1,"locked",
            (byte)2,"unactivated"
    );
}
