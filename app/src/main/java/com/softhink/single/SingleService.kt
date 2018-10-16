package com.softhink.single

import com.softhink.single.models.request.LoginRequest
import com.softhink.single.models.request.RegRequest
import com.softhink.single.models.response.BaseResponse
import com.softhink.single.models.response.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface SingleService {

    @POST("/users/login")
    fun login(@Body request:LoginRequest) : Call<BaseResponse<LoginResponse>>

    @POST("/users/register")
    fun userRegistry(@Body request: RegRequest) : Call<BaseResponse<Any>>
}