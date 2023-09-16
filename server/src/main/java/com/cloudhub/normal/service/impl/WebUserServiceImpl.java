package com.cloudhub.normal.service.impl;

import com.cloudhub.normal.dao.FileDao;
import com.cloudhub.normal.dao.FolderDao;
import com.cloudhub.normal.dao.UserDao;
import com.cloudhub.normal.model.User;
import com.cloudhub.normal.model.storage.FileInfo;
import com.cloudhub.normal.model.storage.FolderInfo;
import com.cloudhub.normal.service.WebUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/webs")
public class WebUserServiceImpl implements WebUserService {
    @Resource
    FileDao fileDao;
    @Resource
    FolderDao folderDao;
    @Resource
    UserDao userDao;
    @GetMapping("/webenter")
    @Override
    public String webEnter(@RequestParam(name = "userName",required = false) String userName,
                           @RequestParam(name = "password",required = false) String password, HttpSession session) {
        session.setAttribute("originalUrl","/webs/showdashboard");
        return "webs/loginpagePage";
    }
    @GetMapping("/storeuserinfo")
    @Override
    public String StoreUserInfo(@RequestParam(name = "username", required = true) String userName,
                                @RequestParam(name = "psw", required = true) String password, HttpSession session,HttpServletResponse response){
        if(!userDao.matchUser(userName,password)){
            return "webs/usererror";
        }
        session.setAttribute("userName",userName);
        session.setAttribute("password",password);
        Cookie cookie=new Cookie("name",userName);
        cookie.setMaxAge(10*60);//10分钟
        response.addCookie(cookie);
        String originalUrl=(String)session.getAttribute("originalUrl");
        //重定向
        return "redirect:"+originalUrl;
    }
    @GetMapping("/showdashboard")
    @Override
    public String showDashBoard(Model model,HttpSession session){
        Object userName=session.getAttribute("userName");
        if(userName==null){
            session.setAttribute("originalUrl","/webs/showdashbord");
            return "webenter";
        }
        else{
            List<FileInfo> fileInfos=fileDao.getRootFileList((String)userName);
            List<FolderInfo> folderInfos=folderDao.getRootFolder((String)userName);
            User user=userDao.getUserByName((String)userName);
            model.addAttribute("fileInfos",fileInfos);
            model.addAttribute("folderInfos",folderInfos);
            model.addAttribute("user", user);
            return "chooselocation";
        }
    }
}
