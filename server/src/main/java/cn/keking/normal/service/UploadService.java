package cn.keking.normal.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

public interface UploadService {
    /**
     * 上传文件
     * @param userId
     * @param name
     * @param hash
     * @param size
     * @param type
     * @param pid
     * @return
     */
    ResponseEntity<String> CheckUploadFile(@RequestParam(value="userId",required = true) String userId ,
                                           @RequestParam(value="name")String name,
                                           @RequestParam(value="hash")String hash,
                                           @RequestParam(value="size")String size,
                                           @RequestParam(value="type")String type,
                                           @RequestParam(value="pid")String pid);
}
