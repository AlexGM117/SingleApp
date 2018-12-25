package com.softhink.single

import com.softhink.single.base.BaseCallback
import com.softhink.single.models.request.LoginRequest
import com.softhink.single.models.request.RegistroRequest
import com.softhink.single.models.response.UserResponse

class SingleRepository {

    private var singleClient = SingleClient.getInstance()

    fun callLogin(request: LoginRequest, callback: BaseCallback<UserResponse>){
        singleClient.login(request)
                .enqueue(callback)
    }

    fun callRegistro(request: RegistroRequest, callback: BaseCallback<UserResponse>){
        singleClient.userRegistro(request)
                .enqueue(callback)
    }
}