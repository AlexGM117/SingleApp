package com.softhink.single.models.request

import com.google.gson.annotations.SerializedName
import com.softhink.single.models.AppInfo

import java.io.Serializable

data class LoginRequest(
        @SerializedName("user") var username: String?,
        @SerializedName("password") var password: String?) : Serializable{

    constructor() : this(null, null)
}
