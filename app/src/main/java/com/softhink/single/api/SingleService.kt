package com.softhink.single.api

import com.softhink.single.data.remote.request.*
import com.softhink.single.data.remote.response.*
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface SingleService {

    @POST("registro/login")
    fun login(@Body request : LoginRequest) : Deferred<Response<BaseResponse<UserResponse>>>

    @POST("registro/alta")
    fun userRegistro(@Body request : SignUpRequest) : Deferred<Response<BaseResponse<UserResponse>>>

    @POST("registro/update2")
    fun userUpdate(@Body request: UserRequest) : Deferred<Response<BaseResponse<UserResponse>>>

    @POST("registro/myProfile")
    fun myProfile(@Body request: UserTest) : Deferred<Response<BaseResponse<UserProfile>>>

    @GET("catalogo/catalogos2")
    fun getCatalogs() : Deferred<Response<BaseResponse<SurveyResponse>>>

    @POST("registro/encuesta")
    fun requestSendSurvey(@Body request: SurveyRequest): Deferred<Response<BaseResponse<EncuestaResponse>>>

    @POST("single/singlear")
    fun requestSinglear(@Body request: SinglearRequest): Deferred<Response<BaseResponse<List<UserResponse>>>>
}