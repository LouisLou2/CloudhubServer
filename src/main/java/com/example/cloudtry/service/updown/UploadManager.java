package com.example.cloudtry.service.updown;

import com.alibaba.fastjson.JSONObject;
import com.example.cloudtry.dao.BaseDao;
import com.example.cloudtry.model.storage.FileInfo;
import com.example.cloudtry.utils.JsonTools;
import com.example.cloudtry.utils.ObsTools;
import com.example.cloudtry.utils.URLTools;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
public class UploadManager {
    @GetMapping("/test")
    public ResponseEntity<String> test(@RequestParam String userId) {
        // 设置响应头，指定 Content-Type 为 application/json
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
        // 返回 JSON 数据作为响应
        return ResponseEntity.ok()
                .headers(headers)
                .body("sec");
    }
    @GetMapping("/upload")
    public ResponseEntity<String> CheckUploadFile(@RequestParam String userId , String fileId, String fileName, String fileSize, String folderId,String ancestors) {

        FileInfo afile=new FileInfo(null,0L,null, null,0L,null, 0L,null,null, null, null);
        JSONObject uploadInfo = JsonTools.initRawUploadInfo();
        boolean exists=BaseDao.SameFileCheck(userId,fileId);
        //如果存在唯一码相同的文件，则调用BaseDao的addAFile方法，其中afile的bucketName和obsObjectKey属性只是null
        if(exists){
            BaseDao.addAFile(exists,afile);
        }
        else{
            //配好bucketName和obsObjectKey
            String properBucketName = ObsTools.getProperBucketName();
            String ObsObjectKey = URLTools.makeObsObjectKey(fileName,fileId);
            //更新afile的bucketName和obsObjectKey属性
            afile.setBucketName(properBucketName);
            afile.setObjectKey(ObsObjectKey);
            //调用BaseDao的addAFile方法,此时的afile的bucketName和obsObjectKey属性是有效的
            BaseDao.addAFile(exists,afile);
            //更新uploadInfo的demand属性为true
            //更新uploadInfo的bucketName和obsObjectKey属性
            //配备好华为云的账号信息
            uploadInfo.put("demand",true);
            uploadInfo.put("bucketName", properBucketName);
            uploadInfo.put("objectKey", ObsObjectKey);
            uploadInfo= JsonTools.AddObsAccountInfo(uploadInfo);
        }
        //JSONObject downloadInfo = BaseDao.getUploadInfo(userId,fileId);
        String uploadInfoString = uploadInfo.toJSONString();
        // 设置响应头，指定 Content-Type 为 application/json
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
        // 返回 JSON 数据作为响应
        return ResponseEntity.ok()
                .headers(headers)
                .body(uploadInfoString);
    }
}
