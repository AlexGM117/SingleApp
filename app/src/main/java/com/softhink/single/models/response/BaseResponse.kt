package com.softhink.single.models.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class BaseResponse<T, U> : Serializable {

    @SerializedName("responseMessage")
    var responseMessage: String? = null
    @SerializedName("responseCode")
    private var responseCode: Int? = null
    @SerializedName("responseResult")
    var result: T? = null

    @SerializedName("responseErrors")
    var error: U? = null

    fun getResponseCode(): Int {
        return responseCode!!
    }

    fun setResponseCode(responseCode: Int?) {
        this.responseCode = responseCode
    }
}