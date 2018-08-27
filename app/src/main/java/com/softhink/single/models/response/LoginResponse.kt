package com.softhink.single.models.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class LoginResponse : Serializable {

    @SerializedName("user")
    var user: User? = null

}
