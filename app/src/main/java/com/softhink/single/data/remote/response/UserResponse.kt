package com.softhink.single.data.remote.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class UserResponse: Serializable {
    @SerializedName("idUsuario")
    var idUsuario: Int? = null
    @SerializedName("fullName")
    var fullName: String? = null
    @SerializedName("birthdate")
    var birthDate: String? = null
    @SerializedName("idPerfil")
    var idPerfil: Int? = null
    @SerializedName("email")
    var email: String? = null
    @SerializedName("username")
    var username: String? = null
    @SerializedName("password")
    var password: String? = null
    @SerializedName("sex")
    var sex: String? = null
    @SerializedName("activo")
    var activo: String? = null
    @SerializedName("token")
    var token: String? = null
}
