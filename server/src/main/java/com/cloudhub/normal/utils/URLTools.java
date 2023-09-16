package com.cloudhub.normal.utils;

import com.cloudhub.normal.common.constant.AppConstants;
import com.cloudhub.normal.dao.FileDao;
import com.cloudhub.normal.dao.impl.FileDaoImpl;
import com.cloudhub.normal.model.BucketAndOb;
import com.obs.services.ObsClient;
import com.obs.services.model.HttpMethodEnum;
import com.obs.services.model.TemporarySignatureRequest;
import com.obs.services.model.TemporarySignatureResponse;

import javax.annotation.Resource;

public class URLTools {
    @Resource
    private static FileDao fileDao=new FileDaoImpl();
    public static String makeObsObjectKey(String fileName,long fileId) {
        return FileUtils.getExtension(fileName) + "/" + fileId;
    }
    public static String makeObsObjectKey(String fileName,String fileId) {
        return FileUtils.getExtension(fileName) + "/" + fileId;
    }
    public static String makeTempObsURL(long id){
        BucketAndOb bAndO= fileDao.getBucketNameAndObjectKey(id);
        if(bAndO==null)return null;
        ObsClient client=new ObsClient(AppConstants.ObsAccount.AK,AppConstants.ObsAccount.SK, AppConstants.ObsAccount.ENDPOINT);
        long expireSeconds=AppConstants.Preview.EXPIRE_TIME;
        TemporarySignatureRequest request = new TemporarySignatureRequest(HttpMethodEnum.GET, expireSeconds);
        request.setBucketName(bAndO.getBucketName());
        request.setObjectKey(bAndO.getObjectKey());
        TemporarySignatureResponse response = client.createTemporarySignature(request);
        return response.getSignedUrl();
    }
}
