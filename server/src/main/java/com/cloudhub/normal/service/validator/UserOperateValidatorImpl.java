package com.cloudhub.normal.service.validator;

import com.cloudhub.normal.common.enums.RequestEnum;
import org.springframework.stereotype.Service;

@Service("UserOperateValidator")
public class UserOperateValidatorImpl implements UserOperateValidator{

    @Override
    public boolean isKeyValid(String key) {
        for(RequestEnum.USER_INFO oper:RequestEnum.USER_INFO.values()){
            if(oper.getMsg().equals(key)){
                return true;
            }
        }
        return false;
    }
    @Override
    public boolean hasEditPermission(String key) {
        if(key.equals("totalSpace")||key.equals("usedSpace")){
            return false;
        }
        return true;
    }
}
