package cn.keking.normal.utils;

import cn.keking.normal.common.constant.AppConstants;
import cn.keking.normal.dao.FileDao;
import com.alibaba.fastjson.JSONObject;
import com.obs.services.ObsClient;
import com.obs.services.model.HttpMethodEnum;
import com.obs.services.model.TemporarySignatureRequest;
import com.obs.services.model.TemporarySignatureResponse;

import javax.annotation.Resource;

public class URLTools {
    @Resource
    private static FileDao fileDao;
    public static String makeObsObjectKey(String fileName,long fileId) {
        return FileUtils.getExtension(fileName) + "/" + fileId;
    }
    public static String makeObsObjectKey(String fileName,String fileId) {
        return FileUtils.getExtension(fileName) + "/" + fileId;
    }
    public static String makeTempObsURL(long id){
        JSONObject bAndO= fileDao.getBucketNameAndObjectKey(id);
        if(bAndO==null)return null;
        ObsClient client=new ObsClient(AppConstants.ObsAccount.AK,AppConstants.ObsAccount.SK, AppConstants.ObsAccount.ENDPOINT);
        long expireSeconds=AppConstants.Preview.EXPIRE_TIME;
        TemporarySignatureRequest request = new TemporarySignatureRequest(HttpMethodEnum.GET, expireSeconds);
        request.setBucketName(bAndO.getString("bucketName"));
        request.setObjectKey(bAndO.getString("objectKey"));
        TemporarySignatureResponse response = client.createTemporarySignature(request);
        return response.getSignedUrl();
    }
}
