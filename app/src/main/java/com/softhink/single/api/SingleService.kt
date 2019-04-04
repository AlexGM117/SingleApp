package com.softhink.single.api

import com.softhink.single.data.remote.request.LoginRequest
import com.softhink.single.data.remote.request.SaveSurveyRequest
import com.softhink.single.data.remote.request.SignUpRequest
import com.softhink.single.data.remote.request.UserTest
import com.softhink.single.data.remote.response.BaseResponse
import com.softhink.single.data.remote.response.EncuestaResponse
import com.softhink.single.data.remote.response.SurveyResponse
import com.softhink.single.data.remote.response.UserResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface SingleService {

    @POST("registro/login")
    fun login(@Body request : LoginRequest) : Call<BaseResponse<UserResponse>>

    @POST("registro/alta")
    fun userRegistro(@Body request : SignUpRequest) : Call<BaseResponse<UserResponse>>

    @GET("catalogo/catalogos")
    fun getCatalogs() : Call<BaseResponse<List<SurveyResponse>>>

    @POST("registro/encuesta")
    fun requestSendSurvey(@Body request: SaveSurveyRequest): Call<BaseResponse<EncuestaResponse>>

    @POST("single/match")
    fun requestSinglear(@Body request: UserTest): Call<BaseResponse<List<UserResponse>>>
}
