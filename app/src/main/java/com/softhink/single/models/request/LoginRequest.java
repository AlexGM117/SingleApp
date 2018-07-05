package com.softhink.single.models.request;

import com.google.gson.annotations.SerializedName;
import com.softhink.single.models.AppInfo;

import java.io.Serializable;

public class LoginRequest implements Serializable {

    @SerializedName("password")
    private String password;
    @SerializedName("username")
    private String username;
    @SerializedName("appInfo")
    private AppInfo appInfo;

    public LoginRequest(String password, String username) {
        this.appInfo = new AppInfo();
        this.password = password;
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public AppInfo getAppInfo() {
        return appInfo;
    }

}
