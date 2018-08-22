package com.softhink.single

import com.softhink.single.models.request.LoginRequest
import com.softhink.single.models.request.RegRequest
import com.softhink.single.models.response.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface SingleService {

    @POST("/users/login")
    fun login(@Body request:LoginRequest) : Call<LoginResponse>

    @POST("/users/register")
    fun registroDeUsuario(@Body request: RegRequest) : Call<Any>
}