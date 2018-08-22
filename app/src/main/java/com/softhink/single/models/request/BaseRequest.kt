package com.softhink.single.models.request

import com.google.gson.annotations.SerializedName
import com.softhink.single.models.AppInfo

abstract class BaseRequest {
    @SerializedName("appInfo")
    protected var appInfo = AppInfo()
}