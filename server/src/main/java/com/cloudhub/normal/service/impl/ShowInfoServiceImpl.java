package com.cloudhub.normal.service.impl;

import com.cloudhub.normal.common.enums.RequestEnum;
import com.cloudhub.normal.common.result.AppResultCode;
import com.cloudhub.normal.dao.FileDao;
import com.cloudhub.normal.dao.FolderDao;
import com.cloudhub.normal.model.storage.FileInfo;
import com.cloudhub.normal.model.storage.FolderInfo;
import com.cloudhub.normal.service.ShowInfoService;
import com.cloudhub.normal.utils.JsonTools;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
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
public class ShowInfoServiceImpl implements ShowInfoService {
    @Resource
    FileDao fileDao;
    @Resource
    FolderDao folderDao;
    @GetMapping("/showinfo")
    @Override
    public ResponseEntity<String> showInfo(@RequestParam(value="folderId") String folderId) {
        JSONObject result = JsonTools.initRawResponseInfo(RequestEnum.LIST);
        List<FileInfo> fileRootList = fileDao.getFileInfoListByFolderId(Long.parseLong(folderId));
        List<FolderInfo> folderRootList = folderDao.getChildrenFolder(Long.parseLong(folderId));
        JSONArray resultJson = new JSONArray();
        for(FileInfo fileInfo:fileRootList){
            JSONObject fileJson = (JSONObject) JSONObject.toJSON(fileInfo);
            fileJson.put("baseType",1);
            resultJson.add(fileJson);
        }
        for(FolderInfo folderInfo:folderRootList){
            JSONObject folderJson = (JSONObject) JSONObject.toJSON(folderInfo);
            folderJson.put("baseType",0);
            resultJson.add(folderJson);
        }
        result.put("resultCode", AppResultCode.OPERATE.SUCCESS.getCode());
        result.put("childList",resultJson);
        String resultStr = result.toJSONString();
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
        // 返回 JSON 数据作为响应
        return ResponseEntity.ok()
                .headers(headers)
                .body(resultStr);
    }
    @GetMapping("/childfolderinfo")
    public ResponseEntity<String> childFolderInfo(@RequestParam(value = "folderId") String folderId){
        JSONObject result = JsonTools.initRawResponseInfo(RequestEnum.LIST);
        List<FolderInfo> folderRootList = folderDao.getChildrenFolder(Long.parseLong(folderId));
        JSONArray resultJson = new JSONArray();
        for(FolderInfo folderInfo:folderRootList){
            JSONObject folderJson = (JSONObject) JSONObject.toJSON(folderInfo);
            folderJson.put("baseType",0);
            resultJson.add(folderJson);
        }
        result.put("resultCode", AppResultCode.OPERATE.SUCCESS.getCode());
        result.put("childList",resultJson);
        String resultStr = result.toJSONString();
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
        // 返回 JSON 数据作为响应
        return ResponseEntity.ok()
                .headers(headers)
                .body(resultStr);
    }
}
