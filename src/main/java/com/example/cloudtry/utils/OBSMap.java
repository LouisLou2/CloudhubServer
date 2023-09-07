package com.example.cloudtry.utils;

import com.alibaba.fastjson.JSONArray;
import com.example.cloudtry.common.constant.AppConstants;
import com.example.cloudtry.model.OBSUser;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;


public class OBSMap {
    public static OBSMap instance=null;
    ConcurrentHashMap<Integer, OBSUser>obsusers = new ConcurrentHashMap<>();
    public OBSMap() {
        JSONArray users = null;
        try {
            users = JsonTools.getJSONArrayFromFile(AppConstants.ObsAccount.OBSACCOUNTJSON);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < Objects.requireNonNull(users).size(); i++) {
            OBSUser obsUser = new OBSUser(users.getJSONObject(i).getString("endPoint"),
                    users.getJSONObject(i).getString("ak"),
                    users.getJSONObject(i).getString("sk"));
            obsusers.put(i, obsUser);
        }
    }
    public static OBSMap getInstances() {
        if(instance==null) {
            instance = new OBSMap();
        }
        return instance;
    }
    public void addOBSUser(int index,OBSUser obsUser) {
        obsusers.put(index,obsUser);
    }
    public static String getOBSPath(String path) {
        //unfinished
        return path;
    }
}
