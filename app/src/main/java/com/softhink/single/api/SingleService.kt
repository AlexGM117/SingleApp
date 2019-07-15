package com.softhink.single.api

import com.softhink.single.data.remote.request.LoginRequest
import com.softhink.single.data.remote.request.SaveSurveyRequest
import com.softhink.single.data.remote.request.SignUpRequest
import com.softhink.single.data.remote.request.UserTest
import com.softhink.single.data.remote.response.BaseResponse
import com.softhink.single.data.remote.response.EncuestaResponse
import com.softhink.single.data.remote.response.SurveyResponse
import com.softhink.single.data.remote.response.UserResponse
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

    @GET("catalogo/catalogos2")
    fun getCatalogs() : Deferred<Response<BaseResponse<List<SurveyResponse>>>>

    @POST("registro/encuesta")
    fun requestSendSurvey(@Body request: SaveSurveyRequest): Deferred<Response<BaseResponse<EncuestaResponse>>>

    @POST("single/match")
    fun requestSinglear(@Body request: UserTest): Deferred<Response<BaseResponse<List<UserResponse>>>>
}
