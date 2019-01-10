package com.softhink.single.data.remote.request

import com.google.gson.annotations.SerializedName
import com.softhink.single.data.model.AppInfo

abstract class BaseRequest {
    @SerializedName("appInfo")
    protected var appInfo = AppInfo()
}