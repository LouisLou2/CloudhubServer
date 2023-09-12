package cn.keking.normal.service.impl;

import cn.keking.normal.common.enums.RequestEnum;
import cn.keking.normal.common.result.AppResultCode;
import cn.keking.normal.dao.FileDao;
import cn.keking.normal.dao.FolderDao;
import cn.keking.normal.dao.UserDao;
import cn.keking.normal.model.User;
import cn.keking.normal.model.storage.FileInfo;
import cn.keking.normal.model.storage.FolderInfo;
import cn.keking.normal.service.UserBaseService;
import cn.keking.normal.service.managers.UserManager;
import cn.keking.normal.service.validator.UserOperateValidator;
import cn.keking.normal.utils.JsonTools;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

//@Service("userService")
@RestController
@RequestMapping("/user")
public class UserBaseServiceImpl implements UserBaseService {
    @Resource
    private FileDao fileDao;
    @Resource
    private FolderDao folderDao;
    @Resource
    private UserDao userDao;
    @Resource
    private UserOperateValidator validator;
    @Override
    @GetMapping("/signup")
    public ResponseEntity<String> initialize(@RequestParam(name = "userName",required = true) String userName,
                                     @RequestParam(name = "phone") String phone,
                                     @RequestParam(name = "password") String password,
                                     @RequestParam(name = "repassword") String repassword) {
        JSONObject jresp= JsonTools.initRawResponseInfo(RequestEnum.SIGN_UP);

        // 检测是否已完成账户信息初始化
        if (userDao.checkUserExist(userName)) {
            jresp.put("resultCode", AppResultCode.SignUp.OCCUPATION_FAILURE.getCode());
        }else{
            jresp.put("resultCode", AppResultCode.SignUp.SUCCESS.getCode());
        }
        // 初始化用户账户信息
        User account = User.initial(userName, phone,password);
        userDao.addUser(account);

        // 创建用户的文件根目录
        //fileService.initialUserRoot(account.getId());

        String jrespStr =jresp.toJSONString();
        // 设置响应头，指定 Content-Type 为 application/json
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
        // 返回 JSON 数据作为响应
        return ResponseEntity.ok()
                .headers(headers)
                .body(jrespStr);
    }

    @Override
    @GetMapping("/signin")
    public ResponseEntity<String> enter(@RequestParam(name = "userName",required = true) String userName,
                                        @RequestParam(name = "password") String password) {

        JSONObject jresp= JsonTools.initRawResponseInfo(RequestEnum.SIGN_IN);
        if(!userDao.matchUser(userName,password)) {
            jresp.put("resultCode", AppResultCode.SignIn.FAILURE.getCode());
        }
        else if(!userDao.checkUserExist(userName)) {
            jresp.put("resultCode", AppResultCode.SignIn.NONEXISTENT.getCode());
        }
        else if(UserManager.getInstance().isLogged(userName)) {
            jresp.put("resultCode", AppResultCode.SignIn.LOGGED_IN.getCode());
        }
        else {
            jresp.put("resultCode", AppResultCode.SignIn.SUCCESS.getCode());
            jresp.put("User", userDao.getUserInfoByUserName(userName));
            List<FileInfo> fileRootList = fileDao.getRootFileList(userName);
            List<FolderInfo> folderRootList = folderDao.getRootFolder(userName);
            JSONArray jsonArray = new JSONArray();
            for(FileInfo fileInfo:fileRootList){
                JSONObject fileJson = (JSONObject) JSON.toJSON(fileInfo);
                fileJson.put("baseType",1);
                jsonArray.add(fileJson);
            }
            for(FolderInfo folderInfo:folderRootList){
                JSONObject folderJson = (JSONObject) JSON.toJSON(folderInfo);
                folderJson.put("baseType",0);
                jsonArray.add(folderJson);
            }
            jresp.put("rootList",jsonArray);
        }
        String jrespStr =jresp.toJSONString();
        // 设置响应头，指定 Content-Type 为 application/json
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
        // 返回 JSON 数据作为响应
        return ResponseEntity.ok()
                .headers(headers)
                .body(jrespStr);
    }
    @GetMapping("/update")
    @Override
    public ResponseEntity<String> update(String key, String id, String newKey) {
        long l_id=Long.parseLong(id);
        JSONObject jresp= JsonTools.initRawResponseInfo(RequestEnum.UPDATE_USER_INFO);
        if(!validator.isKeyValid(key)||!validator.hasEditPermission(key)) {
            jresp.put("resultCode", AppResultCode.USER_UPDATE.REQUEST_ERROR.getCode());
        }
        if(key.equals(RequestEnum.USER_INFO.NAME)){
            if(userDao.checkUserExist(id)){
                jresp.put("resultCode", AppResultCode.USER_UPDATE.HAS_REGISTERED.getCode());
            }else{
                userDao.updateInfo(key,l_id,newKey);
                jresp.put("resultCode", AppResultCode.USER_UPDATE.SUCCESS.getCode());
            }
        }
        String jrespStr =jresp.toJSONString();
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
        // 返回 JSON 数据作为响应
        return ResponseEntity.ok()
                .headers(headers)
                .body(jrespStr);
    }
}
