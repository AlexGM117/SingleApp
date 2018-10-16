package com.softhink.single

import com.softhink.single.models.request.LoginRequest
import com.softhink.single.models.response.LoginResponse


class SingleRepository {

    private var singleClient = SingleClient.getInstance()

    fun callLogin(request: LoginRequest, callback: BaseCallback<LoginResponse>){
        singleClient.login(request)
                .enqueue(callback)
    }
//
//    fun callRegistro(request: RegRequest, interactor: BaseView.Interactor<Any>){
//        singleClient.userRegistry(request)
//                .enqueue(BaseCallback(interactor))
//    }
}