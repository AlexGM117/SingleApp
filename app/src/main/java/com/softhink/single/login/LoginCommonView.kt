package com.softhink.single.login

import com.softhink.single.BaseView

interface LoginCommonView : BaseView {
    fun emailEmpty()
    fun passEmpty()
    fun loginSuccess()
    fun loginFail()
}