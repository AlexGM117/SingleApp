package com.softhink.single.data.manager

import com.softhink.single.api.SingleClient
import com.softhink.single.ui.base.BaseCallback
import com.softhink.single.data.remote.request.LoginRequest
import com.softhink.single.data.remote.request.RegistroRequest
import com.softhink.single.data.remote.response.UserResponse

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

    fun callListGustos(callback: BaseCallback<List<String>>){
        singleClient.getGustos().enqueue(callback)
    }

    fun callListHab(callback: BaseCallback<List<String>>) {
        singleClient.getHabitos().enqueue(callback)
    }
}