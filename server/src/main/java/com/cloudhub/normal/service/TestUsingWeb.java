package com.cloudhub.normal.service;

import com.cloudhub.normal.dao.FileDao;
import com.cloudhub.normal.model.storage.FileInfo;
import com.alibaba.fastjson.JSON;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class TestUsingWeb {
    @Resource
    FileDao fileDao;
    @GetMapping("/test")
    public ResponseEntity<String> test() {
        List<FileInfo> list=fileDao.getFileInfoListByFolderId(12);
        String JSONString = JSON.toJSONString(list);
        String header = "application/json";
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, header);
        // 返回 JSON 数据作为响应
        return ResponseEntity.ok()
                .headers(headers)
                .body(JSONString);
    }
}
