package com.example.cloudtry.utils;

public class URLTools {
    public static String makeObsObjectKey(String fileName,long fileId) {
        return FileUtils.getExtension(fileName) + "/" + fileId;
    }
}
