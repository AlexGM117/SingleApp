package com.softhink.single.api

import com.softhink.single.data.remote.request.LoginRequest
import com.softhink.single.data.remote.request.RegistroRequest
import com.softhink.single.data.remote.response.BaseResponse
import com.softhink.single.data.remote.response.UserResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface SingleService {

    @POST("registro/login")
    fun login(@Body request : LoginRequest) : Call<BaseResponse<UserResponse>>

    @POST("registro/alta")
    fun userRegistro(@Body request : RegistroRequest) : Call<BaseResponse<UserResponse>>

    @GET("catalogo/habitos")
    fun getHabitos() : Call<BaseResponse<List<String>>>

    @GET("catalogo/gustos")
    fun getGustos() : Call<BaseResponse<List<String>>>
}