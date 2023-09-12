package cn.keking.normal.service.impl;

import cn.keking.normal.service.WebUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/webs")
public class WebUserServiceImpl implements WebUserService {
    @GetMapping("/webenter")
    @Override
    public String webEnter(@RequestParam(name = "userName",required = false) String userName,
                           @RequestParam(name = "password",required = false) String password, HttpSession session) {
        return "webs/loginpage";
    }
    @GetMapping("/storeuserinfo")
    @Override
    public String StoreUserInfo(@RequestParam(name = "username", required = true) String userName,
                                @RequestParam(name = "psw", required = true) String password, HttpSession session,HttpServletResponse response){
        session.setAttribute("userName",userName);
        session.setAttribute("password",password);
        Cookie cookie=new Cookie("name",userName);
        cookie.setMaxAge(2*60);//2分钟
        response.addCookie(cookie);
        String originalUrl=(String)session.getAttribute("originalUrl");
        //重定向
        return "redirect:"+originalUrl;
    }
}
