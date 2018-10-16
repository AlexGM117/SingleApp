package com.softhink.single.login

import com.softhink.single.BasePresenter
import com.softhink.single.BaseView
import com.softhink.single.SingleRepository
import com.softhink.single.models.request.LoginRequest
import com.softhink.single.models.response.BaseResponse
import com.softhink.single.models.response.LoginResponse

class LoginCommonPresenter(val view: LoginCommonView) : BasePresenter(),
        BaseView.Interactor<LoginResponse>{

    init {
        repository = SingleRepository()
    }

    fun login(user: String, pss: String){
        println("LOGIN PRESENTER: $user $pss")
        if (user.isEmpty()){
            view.emailEmpty()
        } else if (pss.isEmpty()){
            view.passEmpty()
        } else{
//            repository.callLogin(LoginRequest(pss, user), this)
        }
    }

    override fun onResponseSuccess(response: BaseResponse<LoginResponse>) {
        println(response.responseMessage)
        println(response)
        view.loginSuccess()
    }

    override fun onResponseError() {

    }

    override fun onFailed() {

    }
}