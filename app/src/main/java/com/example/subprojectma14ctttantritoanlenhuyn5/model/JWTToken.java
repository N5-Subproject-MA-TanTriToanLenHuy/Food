package com.example.subprojectma14ctttantritoanlenhuyn5.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JWTToken {

    @SerializedName("token")
    private String token;

    @SerializedName("username")
    private String username;

    public JWTToken() {
    }

    public JWTToken(String token, String username) {
        this.token = token;
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
