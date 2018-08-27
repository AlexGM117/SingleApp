package com.softhink.single.login

import com.softhink.single.BasePresenter
import com.softhink.single.BaseView
import com.softhink.single.Interactor
import com.softhink.single.models.request.LoginRequest
import com.softhink.single.models.response.BaseResponse
import com.softhink.single.models.response.LoginResponse

class LoginCommonPresenter(val view: LoginCommonView) : BasePresenter(),
        BaseView.Interactor<LoginResponse, Any>{

    init {
        interactor = Interactor()
    }

    fun login(user: String?, pss: String?){
        println("LOGIN PRESENTER: $user $pss")
        if (user == null || user.isEmpty()){
            view.emailEmpty()
        } else if (pss == null || pss.isEmpty()){
            view.passEmpty()
        } else{
            interactor.callLogin(LoginRequest(pss, user), this)
        }
    }

    override fun onResponseSuccess(response: BaseResponse<LoginResponse, Any>) {
        println(response.responseMessage)
        println(response)
        view.loginSuccess()
    }

    override fun onResponseError(response: Any) {

    }

    override fun onFailed() {

    }
}