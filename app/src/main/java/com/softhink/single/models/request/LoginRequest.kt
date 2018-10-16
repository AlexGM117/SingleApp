package com.softhink.single.models.request

import com.google.gson.annotations.SerializedName
import com.softhink.single.models.AppInfo

import java.io.Serializable

class LoginRequest : BaseRequest, Serializable {

    @SerializedName("password")
    var password: String? = null
    @SerializedName("username")
    var username: String? = null

    constructor() {}

    constructor(password: String, username: String) {
        this.password = password
        this.username = username
    }

}
