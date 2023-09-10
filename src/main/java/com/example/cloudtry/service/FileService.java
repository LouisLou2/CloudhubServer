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

}
