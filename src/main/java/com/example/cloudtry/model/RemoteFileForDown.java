package com.example.cloudtry.model;

public class RemoteFileForDown {
    private String endPoint;
    private String ak;
    private String sk;

    private String url;
    //getters and setters
    public RemoteFileForDown(String endPoint, String ak, String sk) {
        this.endPoint = endPoint;
        this.ak = ak;
        this.sk = sk;
    }

    public RemoteFileForDown() {
    }

    public String getEndPoint() {
        return endPoint;
    }

    public String getAk() {
        return ak;
    }

    public String getSk() {
        return sk;
    }

    public String getUrl() {
        return url;
    }
}
