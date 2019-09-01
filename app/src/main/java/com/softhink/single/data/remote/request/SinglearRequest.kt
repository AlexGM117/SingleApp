package com.softhink.single.data.remote.request

import com.google.gson.annotations.SerializedName

data class SinglearRequest(@SerializedName("user") var user: String,
                           @SerializedName("latitud") var latitud: String,
                           @SerializedName("longitud") var longitud: String)