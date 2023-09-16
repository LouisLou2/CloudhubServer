package com.cloudhub.normal.utils;

public class ObsTools {
    public static String getProperBucketName() {
        //目前还没有很好的策略来返回最适合的bucketName，所以先返回一个固定的
        return "cloudhub";
    }
}
