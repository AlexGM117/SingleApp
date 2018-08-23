package com.softhink.single

import com.softhink.single.models.request.LoginRequest

class Interactor(private var interactor: BaseView.Interactor<Any>) {

    private var client = SingleClient.getInstance()

    fun callLogin(request: LoginRequest){

        client.login(request).enqueue(InteractorCallback(interactor))
    }
}