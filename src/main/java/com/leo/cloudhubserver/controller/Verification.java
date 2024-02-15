package com.leo.cloudhubserver.controller;

import com.leo.cloudhubserver.model.wrapper.ResponseWrapperProto.ResponseWrapper;
import com.leo.cloudhubserver.service.UserVerification;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Verification {
    @Resource
    UserVerification userVerification;
    @RequestMapping(method = RequestMethod.POST, value = "/verify_user")
    public ResponseWrapper verifyUser(byte type, String info, String password) {
        return userVerification.verifyUser(type, info, password);
    };
}
