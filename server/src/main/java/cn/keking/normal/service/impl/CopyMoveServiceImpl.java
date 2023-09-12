package cn.keking.normal.service.impl;

import cn.keking.normal.common.enums.RequestEnum;
import cn.keking.normal.common.result.AppResultCode;
import cn.keking.normal.dao.FileDao;
import cn.keking.normal.model.storage.MiniInfo;
import cn.keking.normal.service.CopyMoveService;
import cn.keking.normal.service.validator.FileOperateValidator;
import cn.keking.normal.utils.JsonTools;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson2.JSON;
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
public class CopyMoveServiceImpl implements CopyMoveService {

    @Resource
    FileDao fileDao;
    @Resource
    FileOperateValidator validator;

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
        long newOccupation = validator.SpaceEnoughCheck(Long.parseLong(userId), list);
        if (newOccupation == -1L) {
            jresp.put("resultCode", AppResultCode.CopyOrUpload.CLOUD_NOT_ENOUGH.getCode());
        } else {
            //执行复制操作
            fileDao.changeParent(list, l_distId);
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
        if(validator.MoveCheck(l_distId,list)) {
            jresp.put("resultCode", AppResultCode.OPERATE.FAILURE.getCode());
        }
        else{
            //执行移动操作
            fileDao.changeParent(list, l_distId);
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
