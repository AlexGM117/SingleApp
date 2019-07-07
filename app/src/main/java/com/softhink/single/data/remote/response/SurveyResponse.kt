package com.softhink.single.data.remote.response

import com.google.gson.annotations.SerializedName

data class SurveyResponse(
    @SerializedName("consumo") val tastesList: List<BaseObject>,
    @SerializedName("interes") val interestList: List<BaseObject>,
    @SerializedName("preferencia") val genders: List<BaseObject>)