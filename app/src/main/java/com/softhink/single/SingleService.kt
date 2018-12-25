package com.softhink.single

import com.softhink.single.models.request.LoginRequest
import com.softhink.single.models.request.RegistroRequest
import com.softhink.single.models.response.BaseResponse
import com.softhink.single.models.response.UserResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface SingleService {

    @POST("registro/login")
    fun login(@Body request : LoginRequest) : Call<BaseResponse<UserResponse>>

    @POST("registro/alta")
    fun userRegistro(@Body request : RegistroRequest) : Call<BaseResponse<UserResponse>>

    @POST("catalogo/habitos")
    fun getHabitos() : Call<BaseResponse<Any>>

    @POST("catalogo/gustos")
    fun getGustos() : Call<BaseResponse<Any>>
}