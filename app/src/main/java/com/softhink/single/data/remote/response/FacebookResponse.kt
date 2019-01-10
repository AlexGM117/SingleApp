package com.softhink.single.data.remote.response

import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat
import java.util.*

class FacebookResponse {
    @SerializedName("id")
    var id: String? = null
    @SerializedName("name")
    var name: String? = null
    @SerializedName("birthday")
    var birthday: String? = null
        get() = SimpleDateFormat("dd-MM-yyyy", Locale("ES")).format(SimpleDateFormat("MM/dd/yyyy", Locale("ES")).parse(field))
    @SerializedName("email")
    var email: String? = null
    @SerializedName("gender")
    var gender: String? = null
        get() = if (field == "male") "H" else if (field == "female") "M" else ""
    @SerializedName("first_name")
    var first_name: String? = null
    @SerializedName("last_name")
    var last_name: String? = null
}