package com.softhink.single

import com.softhink.single.models.request.LoginRequest
import com.softhink.single.models.request.RegRequest

class Interactor(private var interactor: BaseView.Interactor<Any>) {

    private var client = SingleClient.getInstance()

    fun callLogin(request: LoginRequest){
        client.login(request)
                .enqueue(InteractorCallback(interactor))
    }

    fun callRegistro(request: RegRequest){
        client.registroDeUsuario(request)
                .enqueue(InteractorCallback(interactor))
    }
}