package com.leo.cloudhubserver.controller;

import com.leo.cloudhubserver.model.PersonProto.Person;
import com.leo.cloudhubserver.service.Example;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class req {
    @Resource
    Example example;
    @RequestMapping(method = RequestMethod.GET,value = "/test")
    public Person get() {
        Person it = example.getPerson();
        return it;
    }
}
