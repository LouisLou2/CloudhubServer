package com.cloudhub.normal.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
public class Hello {
    @RequestMapping("/hello")
    public @ResponseBody String hello(){
        return "hello";
    }
}
