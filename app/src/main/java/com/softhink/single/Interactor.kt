package com.softhink.single

import com.softhink.single.models.request.LoginRequest
import com.softhink.single.models.request.RegRequest
import com.softhink.single.models.response.LoginResponse

class Interactor {

    private var client = SingleClient.getInstance()

    fun callLogin(request: LoginRequest, interactor: BaseView.Interactor<LoginResponse, Any>){
        client.login(request)
                .enqueue(BaseCallback(interactor))
    }

    fun callRegistro(request: RegRequest, interactor: BaseView.Interactor<Any, Any>){
        client.userRegistry(request)
                .enqueue(BaseCallback(interactor))
    }
}