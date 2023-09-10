package com.example.cloudtry.dao;

import com.alibaba.fastjson.JSONObject;
import com.example.cloudtry.common.enums.BaseTypeEnum;
import com.example.cloudtry.model.storage.FileInfo;

public class BaseDao {
    /**
     * @param  user_id 用户的唯一id
     * @param file_id 文件的唯一id
     * @return 返回一个JsonObject,以下是返回的格式示例
     * {
     *     "fileanme":"image.jpg",
     *     "bucket":"cloudhub",
     *     "objectKey:"jpg/76ystuw5y46524673f"
     * }
     */

    public static JSONObject getDownloadInfo(String user_id,String file_id){
        //这里不是我的活但是自己写一个例子
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("filename","image.jpg");
        jsonObject.put("bucket","cloudhub");
        jsonObject.put("objectKey",file_id);
        return jsonObject;
    }
    /**
     * @param  user_id 用户的唯一id
     * @param file_id 文件的唯一id
     * @return 返回一个boolean,表示是否存在相同的文件
     */
    public static boolean SameFileCheck(String user_id,long file_id){
        return false;
    }

    /**
     * @Description:
     * @parms: afile 一个文件的最完全的信息
     * @return: boolean 返回一个此文件是否需要真正上传(因为若是已经存在哈希码文件则不需要上传)
     */
    public static void addAFile(boolean exists, FileInfo afile){
        return;
    }

    /*数据库中查找最大的已存在的Id*/
    public static long MaxIdExisted(BaseTypeEnum type){
        return 0L;
    }
    /*更新数据库中查找最大的已存在的*/
    public static void updateMaxId(BaseTypeEnum type,long id){
        return;
    }
}
