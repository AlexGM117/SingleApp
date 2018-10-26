package com.softhink.single

import com.softhink.single.base.BaseCallback
import com.softhink.single.models.request.LoginRequest
import com.softhink.single.models.request.RegRequest

class SingleRepository {

    private var singleClient = SingleClient.getInstance()

    fun callLogin(request: LoginRequest, callback: BaseCallback<Any>){
        singleClient.login(request)
                .enqueue(callback)
    }

    fun callRegistro(request: RegRequest, callback: BaseCallback<Any>){
        singleClient.userRegistry(request)
                .enqueue(callback)
    }
}