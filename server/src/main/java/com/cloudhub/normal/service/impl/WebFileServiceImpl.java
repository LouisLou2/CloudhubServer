package com.cloudhub.normal.service.impl;

import com.cloudhub.normal.dao.FileDao;
import com.cloudhub.normal.dao.FileShareDao;
import com.cloudhub.normal.dao.UserDao;
import com.cloudhub.normal.model.storage.FileInfo;
import com.cloudhub.normal.service.WebFileService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/webs")
public class WebFileServiceImpl implements WebFileService{
    @Resource
    FileDao fileDao;
    @Resource
    UserDao userDao;
    @Resource
    FileShareDao fileShareDao;
    @GetMapping("/getshare")
    @Override
    public String getShareWeb(@RequestParam(name = "id", required = true) String id, Model model, HttpSession session) {
        FileInfo fileInfo=null;
        if(id == null || id.equals("")){
            return "wrongreqPage";
        }
        else if((fileInfo=fileDao.getFileInfoById(Long.parseLong(id)))==null){
            return "notfoundPage";
        }else{
            session.setAttribute("fileinfo",fileInfo);
            return "entercodePage";
        }
    }
    @GetMapping("/checkshare")
    @Override
    public String checkSharePassword(@RequestParam(name = "psw") String psw,Model model, HttpSession session){
        FileInfo fileInfo=(FileInfo) session.getAttribute("fileinfo");
        if(!fileShareDao.sharedFileMatch(fileInfo.getId(),psw)){
            return "wrongpswPage";
        }else{
            model.addAttribute("name",fileInfo.getName());
            model.addAttribute("size", fileInfo.getSize());
            session.setAttribute("targetFileId", fileInfo.getId());
            return "showsharePage";
        }
    }
    @GetMapping("/reservefile")
    @Override
    public String ReserveFile(Model model,HttpSession session){
        Object userName=session.getAttribute("userName");
        Object targetFileId=session.getAttribute("targetFileId");
        if(targetFileId==null){
            return "wrongreqPage";
        }
        if(userName==null){
            session.setAttribute("originalUrl","/webs/reservefile");
            return "webenter";
        }
        else{
            return "chooselocationPage";
        }
    };
    //@GetMapping("/showshare")
    //@Override                             ·
    //public String showSharedFile(Model model) {
    //    return null;
    //}
}
