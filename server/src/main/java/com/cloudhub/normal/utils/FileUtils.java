package com.cloudhub.normal.utils;

public class FileUtils {
    /**method Explanation.
    * @Author:Louis
    * @2023/9/6 22:45
    * @description:
    * @parms: 提供文件名
    * @return: 返回文件后缀名，如果没有后缀名则以“emptyext”为默认后缀名
    */
    public static String getExtension(String fileName) {
        String extension= fileName.substring(fileName.lastIndexOf(".") + 1);
        return (extension.equals(""))?"emptyext":extension;
    }
    /** @description:
     *  @parms: 提供文件名
    *  @return: 返回文件除去后缀名的基名,如果没有基名则以“emptybase”为默认基名
    */
    public static String basenameWithoutEx(String fileName) {
        String base= fileName.substring(0,fileName.lastIndexOf("."));
        return (base.equals(""))?"emptybase":base;
    }
}
