package com.softhink.single.data.remote.request

import com.google.gson.annotations.SerializedName
import com.softhink.single.data.remote.response.BaseObject

data class SurveyRequest(
        @SerializedName("username") var username: String?,
        @SerializedName("visible") var visible: String?,
        @SerializedName("minima") var minima: String?,
        @SerializedName("maxima") var maxima: String?,
        @SerializedName("preferencia") var preferencia: List<BaseObject>?,
        @SerializedName("interes") var intereses: List<BaseObject>?,
        @SerializedName("consumo") var consumo: List<BaseObject>?) {
    constructor() : this(null, null, null ,null ,null, null, null)
}