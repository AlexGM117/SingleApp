package com.softhink.single.data.remote.response

import com.google.gson.annotations.SerializedName

class SurveyResponse {
    @SerializedName("gusto")
    var tastesList: List<BaseObject>? = null
    @SerializedName("habito")
    var interestList: List<BaseObject>? = null
    @SerializedName("preferencia")
    var genders: List<BaseObject>? = null
}