package com.softhink.single.data.remote.response

data class UserResponse(val idUsuario: Int,
                        val fullName: String,
                        var birthDate: String,
                        val idPerfil: Int,
                        var email: String,
                        var username: String,
                        val password: String,
                        var activo: Int,
                        val fechaAlta: String,
                        val token: String,
                        val foto: String,
                        val latitud: String,
                        val longitud: String,
                        val sex: String)