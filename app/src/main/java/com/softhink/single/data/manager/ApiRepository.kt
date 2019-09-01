package com.softhink.single.data.manager

import com.softhink.single.api.SingleClient
import com.softhink.single.data.remote.request.*
import com.softhink.single.data.remote.response.EncuestaResponse
import com.softhink.single.data.remote.response.SurveyResponse
import com.softhink.single.data.remote.response.UserProfile
import com.softhink.single.data.remote.response.UserResponse

class ApiRepository : BaseRepository() {
    val genericMessage = "Por el momento el servicio no esta disponible."

    suspend fun makeRequest(request: LoginRequest) : GenericObserver<UserResponse>? {
        val dataResponse = safeApiCall(
                call = {SingleClient.getInstance().login(request).await()},
                errorMessage = genericMessage)

        return dataResponse
    }

    suspend fun makeRequest(request: SignUpRequest) : GenericObserver<UserResponse>? {
        val dataResponse = safeApiCall(
                call = {SingleClient.getInstance().userRegistro(request).await()},
                errorMessage = genericMessage)

        return dataResponse
    }

    suspend fun makeRequest() : GenericObserver<SurveyResponse>? {
        val dataResponse = safeApiCall(
                call = {SingleClient.getInstance().getCatalogs().await()},
                errorMessage = genericMessage)

        return dataResponse
    }

    suspend fun makeRequest(request: SurveyRequest) : GenericObserver<EncuestaResponse>? {
        val dataResponse =  safeApiCall(
                call = {SingleClient.getInstance().requestSendSurvey(request).await()},
                errorMessage = genericMessage)

        return dataResponse
    }

    suspend fun makeRequest(request: SinglearRequest) : GenericObserver<List<UserResponse>>? {
        val dataResponse =  safeApiCall(
                call = {SingleClient.getInstance().requestSinglear(request).await()},
                errorMessage = genericMessage)

        return dataResponse
    }

    suspend fun makeRequest(request: UserTest) : GenericObserver<UserProfile>? {
        val dataResponse = safeApiCall(
                call = {SingleClient.getInstance().myProfile(request).await()},
                errorMessage = genericMessage)
        return dataResponse
    }

    suspend fun makeRequest(request: UserRequest) : GenericObserver<UserResponse>? {
        val dataResponse = safeApiCall(
                call = {SingleClient.getInstance().userUpdate(request).await()},
                errorMessage = genericMessage)
        return dataResponse
    }
}