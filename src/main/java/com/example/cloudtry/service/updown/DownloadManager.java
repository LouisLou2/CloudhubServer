package com.example.cloudtry.service.updown;

import com.alibaba.fastjson.JSONObject;
import com.example.cloudtry.dao.BaseDao;
import com.example.cloudtry.utils.JsonTools;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
/*流貌似不能直接传，*/
@Controller
public class DownloadManager{

    @GetMapping("/download")
    public ResponseEntity<String> getJsonData(@RequestParam(value="userId",required = true) String userId , @RequestParam(value="fileId",required = true)String fileId) {

        JSONObject downloadInfo = BaseDao.getDownloadInfo(userId,fileId);
        downloadInfo= JsonTools.AddObsAccountInfo(downloadInfo);
        String downloadInfoString = downloadInfo.toJSONString();
        // 设置响应头，指定 Content-Type 为 application/json
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json");

        // 返回 JSON 数据作为响应
        return ResponseEntity.ok()
                .headers(headers)
                .body(downloadInfoString);
    }
}


