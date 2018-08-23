package com.softhink.single.registro.presenter

interface RegAccountContract {

    interface CallbackAccount {
        fun accountData(email: String, pss: String, pss1: String)
    }
}