package com.softhink.single

import com.softhink.single.models.request.LoginRequest
import com.softhink.single.models.request.RegRequest
import com.softhink.single.models.response.BaseResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface SingleService {

    @POST("registro/login")
    fun login(@Body request : LoginRequest) : Call<BaseResponse<String>>

    @POST("users/register")
    fun userRegistry(@Body request : RegRequest) : Call<BaseResponse<GenericObserver<Any>>>
}