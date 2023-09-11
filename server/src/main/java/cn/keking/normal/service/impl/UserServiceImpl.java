package cn.keking.normal.service.impl;

import cn.keking.normal.common.enums.RequestEnum;
import cn.keking.normal.common.result.AppResultCode;
import cn.keking.normal.dao.FileDao;
import cn.keking.normal.dao.UserDao;
import cn.keking.normal.model.User;
import cn.keking.normal.service.UserService;
import cn.keking.normal.service.managers.UserManager;
import cn.keking.normal.utils.JsonTools;
import com.alibaba.fastjson.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//@Service("userService")
@RestController
@RequestMapping("/user")
public class UserServiceImpl implements UserService {

    @Override
    @GetMapping("/signup")
    public ResponseEntity<String> initialize(@RequestParam(name = "userName",required = true) String userName,
                                     @RequestParam(name = "phone") String phone,
                                     @RequestParam(name = "password") String password,
                                     @RequestParam(name = "repassword") String repassword) {
        JSONObject jresp= JsonTools.initRawResponseInfo(RequestEnum.SIGN_UP);

        // 检测是否已完成账户信息初始化
        if (UserDao.checkUserExist(userName)) {
            jresp.put("resultCode", AppResultCode.SignUp.OCCUPATION_FAILURE);
        }else{
            jresp.put("resultCode", AppResultCode.SignUp.SUCCESS);
        }
        // 初始化用户账户信息
        User account = User.initial(userName, phone,password);
        UserDao.insertUser(account);

        // 创建用户的文件根目录
        //fileService.initialUserRoot(account.getId());

        // 修改是否已完成初始化的标志
        //AppProperties.ACCOUNT_HAS_INITIAL = Boolean.TRUE;

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
        if(!UserDao.matchUser(userName,password)) {
            jresp.put("resultCode", AppResultCode.SignIn.FAILURE);
        }
        else if(!UserDao.checkUserExist(userName)) {
            jresp.put("resultCode", AppResultCode.SignIn.NONEXISTENT);
        }
        else if(UserManager.getInstance().isLogged(userName)) {
            jresp.put("resultCode", AppResultCode.SignIn.LOGGED_IN);
        }
        else {
            jresp.put("resultCode", AppResultCode.SignIn.SUCCESS);
            jresp.put("User", UserDao.queryUserByName(userName));
            jresp.put("rootList", FileDao.getRootListByName(userName));
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
}
