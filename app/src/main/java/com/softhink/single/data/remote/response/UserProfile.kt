package com.softhink.single.data.remote.response

import com.google.gson.annotations.SerializedName

data class UserProfile(
        @SerializedName("user") val userId: String,
        @SerializedName("nombre") val userName: String,
        @SerializedName("calificacion") val userScore: Double,
        @SerializedName("descripcion") val description: String,
        @SerializedName("profesion") val job: String,
        @SerializedName("galeria") val galery: List<String>,
        @SerializedName("latitud") val latitud: Double,
        @SerializedName("longitud") val longitud: Double)