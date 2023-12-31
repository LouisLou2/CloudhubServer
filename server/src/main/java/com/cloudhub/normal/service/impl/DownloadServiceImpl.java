package com.cloudhub.normal.service.impl;

import com.cloudhub.normal.common.enums.RequestEnum;
import com.cloudhub.normal.common.result.AppResultCode;
import com.cloudhub.normal.dao.FileDao;
import com.cloudhub.normal.model.BucketAndOb;
import com.cloudhub.normal.service.DownloadService;
import com.cloudhub.normal.utils.JsonTools;
import com.alibaba.fastjson.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
@Controller
@RequestMapping("/storage")
public class DownloadServiceImpl implements DownloadService {
    @Resource
    private FileDao fileDao;
    @GetMapping("/download")
    @Override
    public ResponseEntity<String> getDownloadInfo(@RequestParam(value="id",required = true)String id){
        JSONObject jresp= JsonTools.initRawResponseInfo(RequestEnum.DOWNLOAD);
        BucketAndOb bucketAndObjectKey = fileDao.getBucketNameAndObjectKey(Long.parseLong(id));
        if(bucketAndObjectKey==null){
            jresp.put("resultCode", AppResultCode.OPERATE.FAILURE.getCode());
        }
        else{
            jresp.put("resultCode", AppResultCode.OPERATE.SUCCESS.getCode());
            JSONObject obs=new JSONObject();
            obs.put("bucketName",bucketAndObjectKey.getBucketName());
            obs.put("objectKey",bucketAndObjectKey.getObjectKey());
            jresp.put("obs",JsonTools.AddObsAccountInfo(obs));
        }
        String jrespStr = jresp.toJSONString();
        // 设置响应头，指定 Content-Type 为 application/json
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json");

        // 返回 JSON 数据作为响应
        return ResponseEntity.ok()
                .headers(headers)
                .body(jrespStr);
    }
}
