package com.cloudhub.normal.service.impl;

import com.cloudhub.normal.common.constant.AppConstants;
import com.cloudhub.normal.common.enums.BaseTypeEnum;
import com.cloudhub.normal.common.enums.RequestEnum;
import com.cloudhub.normal.common.result.AppResultCode;
import com.cloudhub.normal.dao.FileDao;
import com.cloudhub.normal.dao.FileMetaDao;
import com.cloudhub.normal.dao.FolderDao;
import com.cloudhub.normal.model.storage.FileInfo;
import com.cloudhub.normal.model.storage.FileMeta;
import com.cloudhub.normal.model.storage.FolderInfo;
import com.cloudhub.normal.model.storage.MiniInfo;
import com.cloudhub.normal.service.DeleteService;
import com.cloudhub.normal.utils.JsonTools;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson2.JSON;
import com.obs.services.ObsClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;
@Controller
@RequestMapping("/storage")
public class DeleteServiceImpl implements DeleteService {
    @Resource
    private FileDao fileDao;
    @Resource
    private FileMetaDao fileMetaDao;
    @Resource
    private FolderDao folderDao;
    @GetMapping("/batch_delete")
    @Override
    public ResponseEntity<String> batchDelete(@RequestParam(value="pairList") String pairList){
        //参数解析
        List<MiniInfo> list = JSON.parseObject(pairList, List.class);
        //初始化回复信息体
        JSONObject jresp = JsonTools.initRawResponseInfo(RequestEnum.DELETE);
        for(MiniInfo miniInfo:list){
            if(miniInfo.getBaseType()== BaseTypeEnum.FILE){
                baseDeleteFile(miniInfo.getId());
            }
            else{
                deleteFolderByFolderId(miniInfo.getId());
            }
        }
        jresp.put("resultCode", AppResultCode.OPERATE.SUCCESS.getCode());
        String jrespStr = JSON.toJSONString(jresp);
        // 设置响应头，指定 Content-Type 为 application/json
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
        return ResponseEntity.ok()
                .headers(headers)
                .body(jrespStr);
    }

    @GetMapping("/deleteFile")
    public ResponseEntity<String> deleteFile(@RequestParam(value = "id") String id) {
        long l_id = Long.parseLong(id);
        JSONObject deleteResult = JsonTools.initRawResponseInfo(RequestEnum.DELETE);
        // 检查文件是否存在
        FileInfo fileInfo = fileDao.getFileInfoById(l_id);
        if (fileInfo != null){
            // 从数据库中删除文件记录
            baseDeleteFile(l_id);
            deleteResult.put("resultCode",AppResultCode.OPERATE.SUCCESS.getCode());
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
        // 返回 JSON 数据作为响应
        return ResponseEntity.ok()
                .headers(headers)
                .body(deleteResult.toString());
    }

    //删除文件夹
    //需要连同里面一层一层的逻辑都需要删除
    @GetMapping("/deleteFolder")
    public ResponseEntity<String> deleteFolder(@RequestParam(value = "id") String id){
        JSONObject deleteResult = JsonTools.initRawResponseInfo(RequestEnum.DELETE);
        deleteFolderByFolderId(Long.parseLong(id));
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
        // 返回 JSON 数据作为响应
        return ResponseEntity.ok()
                .headers(headers)
                .body(deleteResult.toString());
    }
    private void baseDeleteFile(long id){
        fileDao.deleteFile(id);
        String hash=fileDao.getHashByFileId(id);
        FileMeta filemeta = fileMetaDao.getFileMetaByHash(hash);
        String bucketName = filemeta.getBucketName();
        String objectKey = filemeta.getObjectKey();
        int count = fileMetaDao.decreaseCountByHash(hash);
        //如果已经是最后一份文件，那么在云服务器上也需要删除对象
        if(count ==0){
            deleteObsFile(bucketName,objectKey);
        }
    }
    private void deleteObsFile(String bucketName, String objectKey){
        ObsClient obsClient = new ObsClient(AppConstants.ObsAccount.AK,AppConstants.ObsAccount.SK,AppConstants.ObsAccount.ENDPOINT);
        obsClient.deleteObject(bucketName,objectKey);
    }
    private void deleteFolderByFolderId(long id){
        List<FolderInfo> childrenFolderList = folderDao.getChildrenFolder(id);
        List<FileInfo> childFileList = fileDao.getFileInfoListByFolderId(id);
        for(FileInfo child:childFileList){
            baseDeleteFile(child.getId());
        }
        for(FolderInfo child:childrenFolderList){
            deleteFolderByFolderId(child.getId());
        }
        folderDao.deleteFolderByFolderId(id);
    }
}
