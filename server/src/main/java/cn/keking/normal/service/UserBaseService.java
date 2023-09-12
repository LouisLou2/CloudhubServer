package cn.keking.normal.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 用户服务接口
 */
public interface UserBaseService {

    /**
     * 初始化账户信息
     *
     */
    ResponseEntity<String> initialize(@RequestParam(name = "userName") String userName,
                              @RequestParam(name = "phone") String email,
                              @RequestParam(name = "password") String password,
                              @RequestParam(name = "repassword") String repassword);

    /**
     * 登录
     * @param userName
     * @param password
     * @return
     */
    ResponseEntity<String> enter(@RequestParam(name = "userName",required = true) String userName,
                                        @RequestParam(name = "password") String password);
    ResponseEntity<String> update(@RequestParam(name = "key",required = true) String key,
                                  @RequestParam(name = "userId") String userId,
                                 @RequestParam(name = "newKey") String newKey);
}
