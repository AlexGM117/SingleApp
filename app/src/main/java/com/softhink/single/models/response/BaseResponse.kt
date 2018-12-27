package com.softhink.single.models.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class BaseResponse<T> : Serializable {
    @SerializedName("code")
    var responseCode: String? = null
    @SerializedName("message")
    var responseMessage: String? = null
    @SerializedName("body")
    var responseData: T? = null
}