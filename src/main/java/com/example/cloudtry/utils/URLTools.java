package com.example.cloudtry.utils;

public class URLTools {
    public static String makeObsObjectKey(String fileName,String fileId) {
        return FileUtils.getExtension(fileName) + "/" + fileId;
    }
}
