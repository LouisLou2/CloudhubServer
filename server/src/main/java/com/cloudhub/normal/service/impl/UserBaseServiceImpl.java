package com.cloudhub.normal.service.impl;

import com.cloudhub.normal.common.enums.RequestEnum;
import com.cloudhub.normal.common.result.AppResultCode;
import com.cloudhub.normal.dao.FileDao;
import com.cloudhub.normal.dao.FolderDao;
import com.cloudhub.normal.dao.UserDao;
import com.cloudhub.normal.model.User;
import com.cloudhub.normal.model.storage.FileInfo;
import com.cloudhub.normal.model.storage.FolderInfo;
import com.cloudhub.normal.service.UserBaseService;
import com.cloudhub.normal.service.managers.UserManager;
import com.cloudhub.normal.service.validator.UserOperateValidator;
import com.cloudhub.normal.utils.JsonTools;
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
    public ResponseEntity<String> initialize(@RequestParam(name = "name",required = true) String name,
                                     @RequestParam(name = "phone") String phone,
                                     @RequestParam(name = "password") String password) {
        JSONObject jresp= JsonTools.initRawResponseInfo(RequestEnum.SIGN_UP);

        // 检测是否已完成账户信息初始化
        if (userDao.checkUserExist(name)) {
            jresp.put("resultCode", AppResultCode.SignUp.OCCUPATION_FAILURE.getCode());
        }else{
            jresp.put("resultCode", AppResultCode.SignUp.SUCCESS.getCode());
        }
        // 初始化用户账户信息
        User account = User.initial(name, Long.parseLong(phone),password);
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
    public ResponseEntity<String> enter(@RequestParam(name = "name",required = true) String name,
                                        @RequestParam(name = "password") String password) {

        JSONObject jresp= JsonTools.initRawResponseInfo(RequestEnum.SIGN_IN);
        if(!userDao.matchUser(name,password)) {
            jresp.put("resultCode", AppResultCode.SignIn.FAILURE.getCode());
        }
        else if(!userDao.checkUserExist(name)) {
            jresp.put("resultCode", AppResultCode.SignIn.NONEXISTENT.getCode());
        }
        else if(UserManager.getInstance().isLogged(name)) {
            jresp.put("resultCode", AppResultCode.SignIn.LOGGED_IN.getCode());
        }
        else {
            jresp.put("resultCode", AppResultCode.SignIn.SUCCESS.getCode());
            jresp.put("User", userDao.getUserByName(name));
            List<FileInfo> fileRootList = fileDao.getRootFileList(name);
            List<FolderInfo> folderRootList = folderDao.getRootFolder(name);
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
    public ResponseEntity<String> update(@RequestParam(value="key") String key, @RequestParam(value="id") String id, @RequestParam(value="value") String value) {
        long l_id=Long.parseLong(id);
        JSONObject jresp= JsonTools.initRawResponseInfo(RequestEnum.UPDATE_USER_INFO);
        if(!validator.isKeyValid(key)||!validator.hasEditPermission(key)) {
            jresp.put("resultCode", AppResultCode.USER_UPDATE.REQUEST_ERROR.getCode());
        }
        if(key.equals(RequestEnum.USER_INFO.NAME)){
            if(userDao.checkUserExist(id)){
                jresp.put("resultCode", AppResultCode.USER_UPDATE.HAS_REGISTERED.getCode());
            }else{
                userDao.updateInfo(key,l_id,value);
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
