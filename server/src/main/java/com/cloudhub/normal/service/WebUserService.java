package com.cloudhub.normal.service;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public interface WebUserService {
    /**
     * 网页端登录
     */
    String webEnter(@RequestParam(name = "userName",required = false) String userName,
                    @RequestParam(name = "password",required = false) String password, HttpSession session);
    String StoreUserInfo(@RequestParam(name = "userName",required = true) String userName,
                         @RequestParam(name = "password",required = true) String password, HttpSession session, HttpServletResponse response);
    String showDashBoard(Model model, HttpSession session);
}
