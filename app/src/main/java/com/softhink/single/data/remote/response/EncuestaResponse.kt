package com.softhink.single.data.remote.response

import com.google.gson.annotations.SerializedName

class EncuestaResponse {
    @SerializedName("idPerfil")
    var idPerfil: Int? = null
    @SerializedName("nombre")
    var nombre: String? = null
    @SerializedName("descripcion")
    var description: String? = null
    @SerializedName("calificacion")
    var calificacion: Int? = null
    @SerializedName("estatus")
    var estatus: Int? = null
    @SerializedName("foto")
    var foto: String? = null
    @SerializedName("preferencia")
    var preferencia: String? = null
    @SerializedName("activo")
    var activo: Int? = null
    @SerializedName("visible")
    var visible: Int? = null
    @SerializedName("edadMinima")
    var edadMinima: Int? = null
    @SerializedName("edadMaxima")
    var edadMaxima: Int? = null
}