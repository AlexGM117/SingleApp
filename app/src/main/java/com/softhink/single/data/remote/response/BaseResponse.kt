package com.softhink.single.data.remote.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class BaseResponse<T>(
    @SerializedName("code") val responseCode: String,
    @SerializedName("message") val responseMessage: String,
    @SerializedName("body") val responseData: T)