package com.example.cloudtry.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson2.JSON;
import com.example.cloudtry.common.enums.RequestEnum;
import com.example.cloudtry.common.result.AppResultCode;
import com.example.cloudtry.dao.FileDao;
import com.example.cloudtry.model.storage.MiniInfo;
import com.example.cloudtry.service.FileService;
import com.example.cloudtry.service.validator.FileOperateValidator;
import com.example.cloudtry.utils.JsonTools;
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
}
