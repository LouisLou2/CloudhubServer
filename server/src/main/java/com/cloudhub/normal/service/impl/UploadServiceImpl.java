package com.cloudhub.normal.service.impl;

import cn.hutool.core.date.DateUtil;
import com.cloudhub.normal.common.enums.BaseTypeEnum;
import com.cloudhub.normal.common.enums.RequestEnum;
import com.cloudhub.normal.dao.FileDao;
import com.cloudhub.normal.dao.FileMetaDao;
import com.cloudhub.normal.model.storage.FileInfo;
import com.cloudhub.normal.model.storage.FileMeta;
import com.cloudhub.normal.service.UploadService;
import com.cloudhub.normal.service.managers.IdManager;
import com.cloudhub.normal.utils.JsonTools;
import com.cloudhub.normal.utils.ObsTools;
import com.cloudhub.normal.utils.URLTools;
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
public class UploadServiceImpl implements UploadService{
    @Resource
    private FileDao fileDao;
    @Resource
    private FileMetaDao fileMetaDao;
    @GetMapping("/check_upload")
    @Override
    public ResponseEntity<String> CheckUploadFile(@RequestParam(value="userId",required = true) String userId ,
                                                  @RequestParam(value="name")String name,
                                                  @RequestParam(value="hash")String hash,
                                                  @RequestParam(value="size")String size,
                                                  @RequestParam(value="type")String type,
                                                  @RequestParam(value="pid")String pid){
        long id= IdManager.getInstance().generateId(BaseTypeEnum.FILE);
        long l_userId = Long.parseLong(userId);
        long l_pid = Long.parseLong(pid);
        long l_size = Long.parseLong(size);
        int i_type = Integer.parseInt(type);

        FileInfo afile=new FileInfo(id,l_userId,hash, name,l_size, i_type, DateUtil.current(),l_pid);
        JSONObject uploadInfo = JsonTools.initRawResponseInfo(RequestEnum.UPLOAD);
        boolean exists= fileMetaDao.sameFileCheck(hash);
        //如果存在唯一码相同的文件，则调用fileDao的addFile方法，其中afile的bucketName和obsObjectKey属性只是null
        if(exists){
            fileDao.addFile(afile);
        }
        else{
            //配好bucketName和obsObjectKey
            String properBucketName = ObsTools.getProperBucketName();
            String ObsObjectKey = URLTools.makeObsObjectKey(name,id);
            //调用fileDao的addFile方法,此时的afile的bucketName和obsObjectKey属性是有效的
            fileDao.addFile(afile);
            fileMetaDao.addFileMeta(new FileMeta(hash,properBucketName,ObsObjectKey,1));
            //更新uploadInfo的demand属性为true
            //更新uploadInfo的bucketName和obsObjectKey属性
            //配备好华为云的账号信息
            uploadInfo.put("demand",true);
            JSONObject obs = new JSONObject();
            obs.put("bucketName", properBucketName);
            obs.put("objectKey", ObsObjectKey);
            uploadInfo.put("obs", JsonTools.AddObsAccountInfo(obs));
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
