package com.softhink.single.data.remote.request

import com.google.gson.annotations.SerializedName

data class RegistroRequest(@SerializedName("fullName") var fullName: String?,
                           @SerializedName("birthdate") var birthdate: String?,
                           @SerializedName("sex") var sex: String?,
                           @SerializedName("email") var email: String?,
                           @SerializedName("password") var password: String?,
                           @SerializedName("path") var imageProfile: String?) {

    constructor() : this(null, null, null, null, null, null)
}