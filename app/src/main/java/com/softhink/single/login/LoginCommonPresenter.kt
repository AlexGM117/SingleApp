package com.softhink.single.login

class LoginCommonPresenter(val view: LoginCommonView) {

    fun login(user: String?, pss: String?){
        println("LOGIN PRESENTER: $user $pss")
    }
}