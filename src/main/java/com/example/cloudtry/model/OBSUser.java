package com.example.cloudtry.model;

public class OBSUser {
    private String endpoint;
    private String ak;
    private String sk;

    public OBSUser(String endpoint, String ak, String sk) {
        this.endpoint = endpoint;
        this.ak = ak;
        this.sk = sk;
    }

    public OBSUser() {
    }

    public String getEndpoint() {
        return endpoint;
    }

    public String getAk() {
        return ak;
    }

    public String getSk() {
        return sk;
    }
}
