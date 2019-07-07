package com.softhink.single.data.manager

import com.softhink.single.api.SingleClient
import com.softhink.single.data.remote.request.*
import com.softhink.single.ui.base.BaseCallback
import com.softhink.single.data.remote.response.EncuestaResponse
import com.softhink.single.data.remote.response.SurveyResponse
import com.softhink.single.data.remote.response.UserResponse

class SingleRepository {

    private var singleClient = SingleClient.getInstance()

    fun callLogin(request: LoginRequest, callback: BaseCallback<UserResponse>) {
        singleClient.login(request)
                .enqueue(callback)
    }

    fun callRegistro(request: SignUpRequest, callback: BaseCallback<UserResponse>) {
        singleClient.userRegistro(request)
                .enqueue(callback)
    }

    fun callGetCatalogs(callback: BaseCallback<List<SurveyResponse>>) {
        singleClient.getCatalogs().enqueue(callback)
    }

    fun callSendSurvey(request: SaveSurveyRequest, callback: BaseCallback<EncuestaResponse>) {
        singleClient.requestSendSurvey(request).enqueue(callback)
    }

    fun callGetUsers(request: UserTest, callback: BaseCallback<List<UserResponse>>) {
        singleClient.requestSinglear(request).enqueue(callback)
    }
}