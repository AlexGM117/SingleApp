package com.softhink.single.models;

import android.os.Build;

import com.google.gson.annotations.SerializedName;
import com.softhink.single.BuildConfig;

public class AppInfo {
    @SerializedName("versionOs")
    private String versionOs;
    @SerializedName("versionApp")
    private String versionApp;
    @SerializedName("os")
    private Integer os;

    public AppInfo() {
        this.versionOs = String.valueOf(Build.VERSION.SDK_INT);
        this.versionApp = BuildConfig.VERSION_NAME;
        this.os = 1;
    }
}
