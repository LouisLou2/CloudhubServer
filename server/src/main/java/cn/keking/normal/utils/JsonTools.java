package cn.keking.normal.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import cn.keking.normal.common.constant.AppConstants;
import cn.keking.normal.common.enums.RequestEnum;
import cn.keking.normal.common.result.AppResultCode;
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
            case SIGN_UP:
                return initRawSignUpInfo();
            case SIGN_IN:
                return initRawSignInInfo();
            case UPLOAD:
                return initRawUploadInfo();
            case DOWNLOAD:
                return initRawDownloadInfo();
            case PREVIEW:
                return initRawPreviewInfo();
            case COPY:
                return initRawCopyInfo();
            case DELETE:
                return initRawDeleteInfo();
            case LIST:
                return initRawListInfo();
            case UPDATE_USER_INFO:
                return initRawUpdateUserInfo();
            case CREATE_FOLDER:
                return initRawCreateFolderInfo();
            case RENAME:
                return initRawRenameInfo();
            default:
                return null;
        }
    }

    private static JSONObject initRawDeleteInfo() {
        JSONObject obj=new JSONObject();
        obj.put("resultCode", AppResultCode.OPERATE.UNKNOWN_FAILURE.getCode());
        return obj;
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
    public static JSONObject initRawDownloadInfo(){
        JSONObject obj=new JSONObject();
        obj.put("resultCode", AppResultCode.OPERATE.UNKNOWN_FAILURE.getCode());
        obj.put("obs",null);
        return obj;
    }
    public static JSONObject initRawPreviewInfo(){
        JSONObject obj=new JSONObject();
        obj.put("resultCode", AppResultCode.OPERATE.UNKNOWN_FAILURE.getCode());
        return obj;
    }
    public static JSONObject initRawListInfo(){
        JSONObject obj=new JSONObject();
        obj.put("resultCode", AppResultCode.OPERATE.UNKNOWN_FAILURE.getCode());
        return obj;
    }
    public static JSONObject initRawUpdateUserInfo(){
        JSONObject obj=new JSONObject();
        obj.put("resultCode", AppResultCode.USER_UPDATE.UNKNOWN_FAILURE.getCode());
        return obj;
    }
    public static JSONObject initRawCreateFolderInfo(){
        JSONObject obj=new JSONObject();
        obj.put("resultCode", AppResultCode.OPERATE.UNKNOWN_FAILURE.getCode());
        return obj;
    }
    public static JSONObject initRawRenameInfo(){
        JSONObject obj=new JSONObject();
        obj.put("resultCode", AppResultCode.OPERATE.UNKNOWN_FAILURE.getCode());
        return obj;
    }
}
