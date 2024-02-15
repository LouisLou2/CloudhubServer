package com.leo.cloudhubserver.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.leo.cloudhubserver.constant.UserStatusEnum;
import com.leo.cloudhubserver.constant.VerificationConstant;
import com.leo.cloudhubserver.model.User;
import com.leo.cloudhubserver.model.wrapper.ResponseWrapperProto.ResponseWrapper;
import com.leo.cloudhubserver.repository.mapper.UserMapper;
import com.leo.cloudhubserver.util.ConvertUtil;
import com.leo.cloudhubserver.util.Converter;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class UserVerification {
    @Resource
    UserMapper userMapper;
    @Resource
    Converter converter;
    public ResponseWrapper verifyUser(byte fieldCode, String info, String password) {
        var respBuilder = ResponseWrapper.newBuilder();
        String field= VerificationConstant.getType(fieldCode);
        if(field==null){
            respBuilder.setCode(1);
            respBuilder.setError("unsupported type");
            return respBuilder.build();
        }
        
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(field,info);
        User user = userMapper.selectOne(queryWrapper);
        String errorMessage=null;
        if(user==null){
            errorMessage="user not found";
        }else if(!user.getPassword().equals(password)){
            errorMessage="password incorrect";
        }else if(user.getStatus()!= UserStatusEnum.NORMAL){
            errorMessage="user is"+UserStatusEnum.STATUSMAP.get(user.getStatus());
        }else{
            respBuilder.setCode(0);
            var it= ConvertUtil.deepToMap(user);
            respBuilder.putAllLoading(it);
            return respBuilder.build();
        }
        respBuilder.setCode(1);
        respBuilder.setError(errorMessage);
        return respBuilder.build();
    }
}
