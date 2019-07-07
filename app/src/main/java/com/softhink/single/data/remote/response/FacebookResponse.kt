package com.softhink.single.data.remote.response

import com.google.gson.annotations.SerializedName

data class FacebookResponse (
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("birthday") val birthday: String,
    @SerializedName("email") val email: String,
    @SerializedName("gender") val gender: String,
    @SerializedName("first_name") val first_name: String,
    @SerializedName("last_name") val last_name: String)