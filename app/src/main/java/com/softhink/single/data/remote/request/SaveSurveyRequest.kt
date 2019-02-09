package com.softhink.single.data.remote.request

import com.google.gson.annotations.SerializedName
import com.softhink.single.data.remote.response.BaseObject

data class SaveSurveyRequest(
        @SerializedName("username") var username: String?,
        @SerializedName("visible") var visible: String?,
        @SerializedName("minima") var minima: String?,
        @SerializedName("maxima") var maxima: String?,
        @SerializedName("preferencia") var preferencia: List<BaseObject>?,
        @SerializedName("gusto") var gustos: List<BaseObject>?,
        @SerializedName("habito") var habitos: List<BaseObject>?) {
    constructor() : this(null, null, null ,null ,null, null, null)
}