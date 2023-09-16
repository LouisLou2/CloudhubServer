package com.cloudhub.normal.service;

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
    ResponseEntity<String> initialize(@RequestParam(name = "name") String name,
                              @RequestParam(name = "phone") String email,
                              @RequestParam(name = "password") String password);

    /**
     * 登录
     * @param name
     * @param password
     * @return
     */
    ResponseEntity<String> enter(@RequestParam(name = "name",required = true) String name,
                                        @RequestParam(name = "password") String password);
    ResponseEntity<String> update(@RequestParam(name = "key",required = true) String key,
                                  @RequestParam(name = "userId") String userId,
                                 @RequestParam(name = "newKey") String newKey);
}
