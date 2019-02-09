package com.softhink.single.data.remote.request

import com.google.gson.annotations.SerializedName

data class SignUpRequest(@SerializedName("usuario") var usuario: UserRequest)