package com.example.cloudtry.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

public interface FileService {

    /**
     * 批量复制文件
     * @param pairList
     * @param distId
     * @param userId
     * @return
     */
    ResponseEntity<String> batchCopy(@RequestParam(value="pairList",required = true) String pairList,
                                     @RequestParam(value="distId",required = true) String distId,
                                     @RequestParam(value="userId",required = true) String userId);

    /**
     * 批量移动文件
     * @param pairList
     * @param distId
     * @param userId
     * @return
     */
    ResponseEntity<String> batchMove(@RequestParam(value="pairList",required = true) String pairList,
                                     @RequestParam(value="distId",required = true) String distId,
                                     @RequestParam(value="userId",required = true) String userId);
    ResponseEntity<String> CheckUploadFile(@RequestParam(value="userId",required = true) String userId ,
                                          @RequestParam(value="name")String name,
                                          @RequestParam(value="hash")String hash,
                                          @RequestParam(value="size")String size,
                                          @RequestParam(value="type")String type,
                                          @RequestParam(value="pid")String pid);
    ResponseEntity<String> getDownloadInfo(@RequestParam(value="userId",required = false) String userId ,
                                           @RequestParam(value="id",required = true)String id);

}
