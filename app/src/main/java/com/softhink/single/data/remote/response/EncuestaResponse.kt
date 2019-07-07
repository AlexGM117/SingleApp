package com.softhink.single.data.remote.response

import com.google.gson.annotations.SerializedName

data class EncuestaResponse(
    @SerializedName("idPerfil") val idPerfil: Int,
    @SerializedName("nombre") val nombre: String,
    @SerializedName("descripcion") val description: String,
    @SerializedName("calificacion") val calificacion: Int,
    @SerializedName("estatus") val estatus: Int,
    @SerializedName("foto") var foto: String,
    @SerializedName("preferencia") val preferencia: String,
    @SerializedName("activo") val activo: Int,
    @SerializedName("visible") val visible: Int,
    @SerializedName("edadMinima") val edadMinima: Int,
    @SerializedName("edadMaxima") val edadMaxima: Int)