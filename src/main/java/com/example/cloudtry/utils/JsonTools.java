package com.example.cloudtry.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.cloudtry.common.constant.AppConstants;
import com.example.cloudtry.common.enums.RequestEnum;
import com.example.cloudtry.common.result.AppResultCode;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

public class JsonTools {
    public static JsonTools instance=null;

    //将一个json文件转换成JSONArray
    public static JSONArray getJSONArrayFromFile(String jsonFile) throws IOException {
        String jsonData = "[]";
        File externalFile = new File(jsonFile);
        if (externalFile.exists())
            jsonData = FileUtils.readFileToString(externalFile, Charset.forName("UTF-8"));
        return JSONArray.parseArray(jsonData);
    }
    public static JSONObject getJSONObjectFromFile(String jsonFile) throws IOException {
        String jsonData = "{}";
        File externalFile = new File(jsonFile);
        if (externalFile.exists())
            jsonData = FileUtils.readFileToString(externalFile, Charset.forName("UTF-8"));
        return JSONObject.parseObject(jsonData);
    }
    public static JSONObject AddObsAccountInfo(JSONObject jsonObject){
        jsonObject.put("endpoint", AppConstants.ObsAccount.ENDPOINT);
        jsonObject.put("ak",AppConstants.ObsAccount.AK);
        jsonObject.put("sk",AppConstants.ObsAccount.SK);
        return jsonObject;
    }
    public static JSONObject AddObsProperBucketAndObjectKey(JSONObject jsonObject,String fileName,String fileId){
        jsonObject.put("bucketName", ObsTools.getProperBucketName());
        jsonObject.put("objectKey", URLTools.makeObsObjectKey(fileName,fileId));
        return jsonObject;
    }
    public static JSONObject initRawResponseInfo(RequestEnum type){
        switch (type){
            case RequestEnum.SIGN_UP:
                return initRawSignUpInfo();
            case RequestEnum.SIGN_IN:
                return initRawSignInInfo();
            case RequestEnum.UPLOAD:
                return initRawUploadInfo();
            case DOWNLOAD:
                return initRawDownloadInfo();
            case DELETE:
                return initRawDeleteInfo();
            case COPY:
                return initRawCopyInfo();
            case MOVE:
                return initRawMoveInfo();
            case RENAME:
                return initRawRenameInfo();
            case MKDIR:
                return initRawMkdirInfo();
            case BATCH_DELETE:
                return initRawBatchDeleteInfo();
            case BATCH_COPY:
                return initRawBatchCopyInfo();
            case BATCH_MOVE:
                return initRawBatchMoveInfo();
            case ROLLBACK_TRASH:
                return initRawRollbackTrashInfo();
            default:
                return null;
        }
    }
    public static JSONObject initRawSignUpInfo(){
        JSONObject obj=new JSONObject();
        obj.put("resultCode", AppResultCode.SignUp.UNKNOWN_FAILURE.getCode());
        return obj;
    }
    public static JSONObject initRawSignInInfo(){
        JSONObject obj=new JSONObject();
        obj.put("resultCode", AppResultCode.SignIn.UNKNOWN_FAILURE.getCode());
        obj.put("User", null);
        obj.put("rootList", null);
        return obj;
    }
    public static JSONObject initRawCopyInfo(){
        JSONObject obj=new JSONObject();
        obj.put("resultCode", AppResultCode.CopyOrUpload.UNKNOWN_FAILURE.getCode());
        obj.put("occupation",null);
        return obj;
    }
    public static JSONObject initRawUploadInfo(){
        JSONObject obj=new JSONObject();
        obj.put("resultCode", AppResultCode.CopyOrUpload.UNKNOWN_FAILURE.getCode());
        obj.put("demand",false);
        return obj;
    }
}
