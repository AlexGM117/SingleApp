package com.softhink.single.data.model

import android.os.Build

import com.google.gson.annotations.SerializedName
import com.softhink.single.BuildConfig

class AppInfo {
    @SerializedName("versionOs")
    private val versionOs: String
    @SerializedName("versionApp")
    private val versionApp: String
    @SerializedName("os")
    private val os: Int?

    init {
        this.versionOs = Build.VERSION.SDK_INT.toString()
        this.versionApp = BuildConfig.VERSION_NAME
        this.os = 1
    }
}
