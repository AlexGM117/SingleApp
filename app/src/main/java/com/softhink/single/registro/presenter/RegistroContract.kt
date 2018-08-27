package com.softhink.single.registro.presenter

import com.softhink.single.BaseView
import java.util.*

interface RegistroContract : BaseView {

    fun succesToSurvey(message: String)
    fun errorMessage(message: String)

    interface DataContract {
        fun nameIsEmpty()
        fun nameStringLenght()
        fun dateEmpty()
        fun isUnderAge()
        fun genderUnselected()
        fun toAccountFragment()

        interface CallbackData{
            fun dataForm(name:String?, date: Date?, gender:String?)
        }
    }

    interface AccountContract {

        fun emailEmpty()
        fun invalidEmail()
        fun passEmpty()
        fun passLenght()
        fun passNotMatch()
        fun finishRegistro()

        interface CallbackAccount {
            fun accountData(email: String?, pss: String?, pss1: String?)
        }
    }

    interface PhotoProfileContract {

        interface CallbackPhoto {
            fun callService(string: String?)
        }
    }
}