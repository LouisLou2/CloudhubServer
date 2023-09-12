package cn.keking.normal.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.keking.normal.common.enums.BaseTypeEnum;
import cn.keking.normal.common.enums.RequestEnum;
import cn.keking.normal.common.result.AppResultCode;
import cn.keking.normal.dao.FileDao;
import cn.keking.normal.dao.FolderDao;
import cn.keking.normal.service.FileUpdateService;
import cn.keking.normal.service.managers.IdManager;
import cn.keking.normal.utils.JsonTools;
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
public class FileUpdateServiceImpl implements FileUpdateService{
    @Resource
    private FileDao fileDao;
    @Resource
    private FolderDao folderDao;

    @GetMapping("/createfolder")
    public ResponseEntity<String> createFolder(@RequestParam(value="name")String name, @RequestParam(value="pid")String pid){
        long l_pid=Long.parseLong(pid);
        JSONObject jresp= JsonTools.initRawResponseInfo(RequestEnum.CREATE_FOLDER);
        boolean exist = folderDao.checkFolderExist(l_pid,name);
        if(exist){
            jresp.put("resultCode", AppResultCode.FILE_FOLDER_UPDATE.SAME_NAME.getCode());
        }else{
            long id= IdManager.getInstance().generateIdAndUpdate(BaseTypeEnum.FOLDER);
            long timeStamp= DateUtil.current();
            folderDao.createNewFolder(id,name,l_pid,timeStamp);
            jresp.put("resultCode",AppResultCode.FILE_FOLDER_UPDATE.SUCCESS.getCode());
        }
        String jrespStr=jresp.toJSONString();
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
        // 返回 JSON 数据作为响应
        return ResponseEntity.ok()
                .headers(headers)
                .body(jrespStr);
    }

    @GetMapping("/rename")
    @Override
    public ResponseEntity<String> rename(@RequestParam(value="baseType")String baseType,@RequestParam(value="id") String id, @RequestParam(value="id")String newName){
        long l_id=Long.parseLong(id);
        //感觉不一定要传userId
        JSONObject jresp= JsonTools.initRawResponseInfo(RequestEnum.RENAME);
        boolean exist=false;
        long pid=0;
        if(baseType.equals(BaseTypeEnum.FILE.getCode())){
            pid=fileDao.getPidByFileId(l_id);
            exist = fileDao.isSameNameExistInFolder(pid,newName);
        }
        if(baseType.equals(BaseTypeEnum.FOLDER.getCode())){
            pid=folderDao.getPidByFolderId(l_id);
            exist = folderDao.checkFolderExist(pid,newName);
        }
        if(exist){
            jresp.put("resultCode",AppResultCode.FILE_FOLDER_UPDATE.SAME_NAME.getCode());
        }else {
            fileDao.updateFileName(l_id,newName);
            jresp.put("resultCode",AppResultCode.FILE_FOLDER_UPDATE.SUCCESS.getCode());
        }
        String jrespStr=jresp.toJSONString();
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
        // 返回 JSON 数据作为响应
        return ResponseEntity.ok()
                .headers(headers)
                .body(jrespStr);
    }
}
