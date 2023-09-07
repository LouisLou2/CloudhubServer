package com.example.cloudtry.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.cloudtry.common.constant.AppConstants;
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
    public static JSONObject initRawUploadInfo(){
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("demand",false);
        return jsonObject;
    }

}
