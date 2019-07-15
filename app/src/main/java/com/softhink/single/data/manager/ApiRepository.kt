package com.softhink.single.data.manager

import com.softhink.single.api.SingleClient
import com.softhink.single.data.remote.request.LoginRequest
import com.softhink.single.data.remote.request.SaveSurveyRequest
import com.softhink.single.data.remote.request.SignUpRequest
import com.softhink.single.data.remote.request.UserTest
import com.softhink.single.data.remote.response.EncuestaResponse
import com.softhink.single.data.remote.response.SurveyResponse
import com.softhink.single.data.remote.response.UserResponse

class ApiRepository : BaseRepository() {

    suspend fun makeRequest(request: LoginRequest) : GenericObserver<UserResponse>? {
        val dataResponse = safeApiCall(
                call = {SingleClient.getInstance().login(request).await()},
                errorMessage = "Error en el servicio")

        return dataResponse
    }

    suspend fun makeRequest(request: SignUpRequest) : GenericObserver<UserResponse>? {
        val dataResponse = safeApiCall(
                call = {SingleClient.getInstance().userRegistro(request).await()},
                errorMessage = "Error en el servicio")

        return dataResponse
    }

    suspend fun makeRequest() : GenericObserver<List<SurveyResponse>>? {
        val dataResponse = safeApiCall(
                call = {SingleClient.getInstance().getCatalogs().await()},
                errorMessage = "Error en el servicio")

        return dataResponse
    }

    suspend fun makeRequest(request: SaveSurveyRequest) : GenericObserver<EncuestaResponse>? {
        val dataResponse =  safeApiCall(
                call = {SingleClient.getInstance().requestSendSurvey(request).await()},
                errorMessage = "Error en el servicio")

        return dataResponse
    }

    suspend fun makeRequest(request: UserTest) : GenericObserver<List<UserResponse>>? {
        val dataResponse =  safeApiCall(
                call = {SingleClient.getInstance().requestSinglear(request).await()},
                errorMessage = "Error en el servicio")

        return dataResponse
    }
}