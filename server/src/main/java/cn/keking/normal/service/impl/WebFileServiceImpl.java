package cn.keking.normal.service.impl;

import cn.keking.normal.common.enums.BaseTypeEnum;
import cn.keking.normal.dao.FileDao;
import cn.keking.normal.dao.FileShareDao;
import cn.keking.normal.dao.UserDao;
import cn.keking.normal.model.storage.FileInfo;
import cn.keking.normal.service.WebFileService;
import cn.keking.normal.service.managers.IdManager;
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
            return "webs/wrongreq";
        }
        else if((fileInfo=fileDao.getFileInfoById(Long.parseLong(id)))==null){
            return "webs/notfound";
        }else{
            session.setAttribute("fileinfo",fileInfo);
            return "webs/sharepage";
        }
    }
    @GetMapping("/checkshare")
    @Override
    public String checkSharePassword(@RequestParam(name = "psw") String psw,Model model, HttpSession session){
        FileInfo fileInfo=(FileInfo) session.getAttribute("fileinfo");
        if(!fileShareDao.sharedFileMatch(fileInfo.getId(),psw)){
            return "webs/wrongpsw";
        }else{
            model.addAttribute("name",fileInfo.getName());
            model.addAttribute("size", fileInfo.getSize());
            session.setAttribute("targetFileId", fileInfo.getId());
            return "webs/showshare";
        }
    }
    @GetMapping("/reservefile")
    @Override
    public String ReserveFile(Model model,HttpSession session){
        Object userName=session.getAttribute("userName");
        Object targetFileId=session.getAttribute("targetFileId");
        if(targetFileId==null){
            return "webs/wrongreq";
        }
        if(userName==null){
            session.setAttribute("originalUrl","/webs/reservefile");
            return "webenter";
        }
        else{
            String userNameStr=(String)userName;
            long l_targetFileId=(long)targetFileId;
            long ownFileId= IdManager.getInstance().generateIdAndUpdate(BaseTypeEnum.FILE);
            long userId= userDao.getUserIdByName(userNameStr);
            fileDao.ReceiveFileById(userId,l_targetFileId,ownFileId);
        }
        return "webs/reserve_success";
    };
    //@GetMapping("/showshare")
    //@Override                             ·
    //public String showSharedFile(Model model) {
    //    return null;
    //}
}
