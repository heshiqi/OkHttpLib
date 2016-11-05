package com.hsq.dome.request;

import java.io.Serializable;

/**
 * Created by zhf on 2016/10/13.
 */

public class RongYunTokenModel implements Serializable{

    private String token;

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
