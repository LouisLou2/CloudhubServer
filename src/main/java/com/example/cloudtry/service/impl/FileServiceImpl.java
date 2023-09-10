package com.example.cloudtry.service.impl;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson2.JSON;
import com.example.cloudtry.common.enums.BaseTypeEnum;
import com.example.cloudtry.common.enums.FileTypeEnum;
import com.example.cloudtry.common.enums.RequestEnum;
import com.example.cloudtry.common.result.AppResultCode;
import com.example.cloudtry.dao.BaseDao;
import com.example.cloudtry.dao.FileDao;
import com.example.cloudtry.model.storage.FileInfo;
import com.example.cloudtry.model.storage.MiniInfo;
import com.example.cloudtry.service.FileService;
import com.example.cloudtry.service.managers.IdManager;
import com.example.cloudtry.service.validator.FileOperateValidator;
import com.example.cloudtry.utils.JsonTools;
import com.example.cloudtry.utils.ObsTools;
import com.example.cloudtry.utils.URLTools;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service("fileService")
@Controller("/storage")
public class FileServiceImpl implements FileService {

    @GetMapping("/batch_copy")
    @Override
    public ResponseEntity<String> batchCopy(@RequestParam(value = "pairList", required = true) String pairList,
                                            @RequestParam(value = "distId", required = true) String distId,
                                            @RequestParam(value = "userId", required = true) String userId) {
        //参数解析
        long l_distId = Long.parseLong(distId);
        long l_userId = Long.parseLong(userId);
        List<MiniInfo> list = JSON.parseObject(pairList, List.class);
        //初始化回复信息体
        JSONObject jresp = JsonTools.initRawResponseInfo(RequestEnum.COPY);
        //空间检验
        long newOccupation = FileOperateValidator.SpaceEnoughCheck(Long.parseLong(userId), list);

        if (newOccupation == -1) {
            jresp.put("resultCode", AppResultCode.CopyOrUpload.CLOUD_NOT_ENOUGH.getCode());
        } else {
            //执行复制操作
            FileDao.changeParent(list, l_distId);
            jresp.put("resultCode", AppResultCode.OPERATE.SUCCESS.getCode());
            jresp.put("occupation", newOccupation);
        }
        String jrespStr = JSON.toJSONString(jresp);
        // 设置响应头，指定 Content-Type 为 application/json
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json");

        // 返回 JSON 数据作为响应
        return ResponseEntity.ok()
                .headers(headers)
                .body(jrespStr);
    }
    @GetMapping("/batch_move")
    @Override
    public ResponseEntity<String> batchMove(String pairList, String distId, String userId) {
        //参数解析
        long l_distId = Long.parseLong(distId);
        long l_userId = Long.parseLong(userId);
        List<MiniInfo> list = JSON.parseObject(pairList, List.class);
        //初始化回复信息体
        JSONObject jresp = JsonTools.initRawResponseInfo(RequestEnum.COPY);
        if(FileOperateValidator.MoveCheck(l_distId,list)) {
            jresp.put("resultCode", AppResultCode.OPERATE.FAILURE.getCode());
        }
        else{
            //执行移动操作
            FileDao.changeParent(list, l_distId);
            jresp.put("resultCode", AppResultCode.OPERATE.SUCCESS.getCode());
        }
        String jrespStr = JSON.toJSONString(jresp);
        // 设置响应头，指定 Content-Type 为 application/json
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
        return ResponseEntity.ok()
                .headers(headers)
                .body(jrespStr);
    }
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
        FileTypeEnum fileType = FileTypeEnum.parseTypeByCode(Integer.parseInt(type));

        FileInfo afile=new FileInfo(id,l_userId,hash, name,l_size, fileType, DateUtil.current(),l_pid,null, null);
        JSONObject uploadInfo = JsonTools.initRawResponseInfo(RequestEnum.UPLOAD);
        boolean exists= BaseDao.SameFileCheck(userId,id);
        //如果存在唯一码相同的文件，则调用BaseDao的addAFile方法，其中afile的bucketName和obsObjectKey属性只是null
        if(exists){
            BaseDao.addAFile(exists,afile);
        }
        else{
            //配好bucketName和obsObjectKey
            String properBucketName = ObsTools.getProperBucketName();
            String ObsObjectKey = URLTools.makeObsObjectKey(name,id);
            //更新afile的bucketName和obsObjectKey属性
            afile.setBucketName(properBucketName);
            afile.setObjectKey(ObsObjectKey);
            //调用BaseDao的addAFile方法,此时的afile的bucketName和obsObjectKey属性是有效的
            BaseDao.addAFile(exists,afile);
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
    @GetMapping("/download")
    @Override
    public ResponseEntity<String> getDownloadInfo(@RequestParam(value="userId",required = false,defaultValue = "123") String userId ,
                                           @RequestParam(value="id",required = true)String id){
        JSONObject jresp=JsonTools.initRawResponseInfo(RequestEnum.DOWNLOAD);
        JSONObject bucketAndObjectKey = FileDao.getBucketNameAndObjectKey(Long.parseLong(id));
        if(bucketAndObjectKey==null){
            jresp.put("resultCode", AppResultCode.OPERATE.FAILURE.getCode());
        }
        else{
            jresp.put("resultCode", AppResultCode.OPERATE.SUCCESS.getCode());
            jresp.put("obs",JsonTools.AddObsAccountInfo(bucketAndObjectKey));
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
