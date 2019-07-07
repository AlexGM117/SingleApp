package com.softhink.single.data.remote.response

data class UserResponse(val idUsuario: Int, val fullName: String, var birthDate: String,
    val idPerfil: Int, var email: String, var username: String, val password: String,
    val sex: String, var activo: String, val token: String, val latitud: String, val longitud: String)
