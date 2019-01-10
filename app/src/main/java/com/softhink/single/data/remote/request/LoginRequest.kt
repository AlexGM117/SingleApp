package com.softhink.single.data.remote.request

import com.google.gson.annotations.SerializedName

import java.io.Serializable

data class LoginRequest(
        @SerializedName("email") var username: String?,
        @SerializedName("password") var password: String?) : Serializable{

    constructor() : this(null, null)
}
