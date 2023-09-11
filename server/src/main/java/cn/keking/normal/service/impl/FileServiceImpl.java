package cn.keking.normal.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.keking.normal.common.enums.BaseTypeEnum;
import cn.keking.normal.common.enums.FileTypeEnum;
import cn.keking.normal.common.enums.RequestEnum;
import cn.keking.normal.common.result.AppResultCode;
import cn.keking.normal.dao.BaseDao;
import cn.keking.normal.dao.FileDao;
import cn.keking.normal.model.storage.FileInfo;
import cn.keking.normal.model.storage.MiniInfo;
import cn.keking.normal.service.FileService;
import cn.keking.normal.service.managers.IdManager;
import cn.keking.normal.service.validator.FileOperateValidator;
import cn.keking.normal.utils.JsonTools;
import cn.keking.normal.utils.ObsTools;
import cn.keking.normal.utils.URLTools;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson2.JSON;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

//@Service("fileService")
@Controller
@RequestMapping("/storage")
public class FileServiceImpl implements FileService {

    private RestTemplate restTemplate=new RestTemplate();

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
    @GetMapping("/onlinepreview")
    @Override
    public RedirectView OnlineFilePreview(@RequestParam(value="id",required = true)String id, RedirectAttributes attributes) {
        String tempURL=URLTools.makeTempObsURL(Long.parseLong(id));
        //JSONObject jresp=JsonTools.initRawResponseInfo(RequestEnum.PREVIEW);
        RedirectView redirectView=null;
        if(tempURL==null){
            redirectView = new RedirectView("/notfoundPage");
            return redirectView;
        }
        String encodedURL= cn.hutool.core.codec.Base64.encode(tempURL);
         //将参数添加到重定向URL
        attributes.addAttribute("url", encodedURL);
         //创建RedirectView并设置重定向地址
        redirectView = new RedirectView("/onlinePreview");
        return redirectView;
    }
    @GetMapping("/notfoundPage")
    @Override
    public String notFoundPage() {
        return "notfound";
    }

    @Override
    @GetMapping("/onlinepreview_deprecated")
    public String OnlineFilePreview(String id) {
        String tempURL="https://cloudhub.obs.cn-south-1.myhuaweicloud.com/afile.jpg";
        String encodedURL= cn.hutool.core.codec.Base64.encode(tempURL);
        //String targetUrl = "192.168.146.121:8082/onlinePreview?url="+encodedURL;
        String targetUrl="https://www.bilibili.com/";
        // 发送GET请求
        ResponseEntity<String> response = restTemplate.getForEntity(targetUrl, String.class);

        // 获取响应数据，您可以根据需要对响应进行处理
        String responseData = response.getBody();
        return responseData;
    }
}
