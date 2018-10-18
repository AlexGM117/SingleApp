package com.softhink.single.registro.presenter

import com.softhink.single.BaseView
import java.util.*

interface RegistroContract : BaseView {

    fun succesToSurvey(message: String)
    fun errorMessage(message: String)

    interface PhotoProfileContract {

        interface CallbackPhoto {
            fun callService(string: String?)
        }
    }
}