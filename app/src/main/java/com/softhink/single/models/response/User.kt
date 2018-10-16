package com.softhink.single.models.response

import com.google.gson.annotations.SerializedName

import java.io.Serializable

class User : Serializable {
    @SerializedName("order")
    var order: Int = 0
    @SerializedName("token")
    var token: String? = null
    @SerializedName("imageProfile")
    var imageProfile: String? = null
    @SerializedName("email")
    var email: String? = null
    @SerializedName("profileType")
    var profileType: Int = 0
    @SerializedName("fullname")
    var fullname: String? = null
    @SerializedName("userId")
    var userId: Int = 0
}
