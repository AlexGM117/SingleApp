package com.softhink.single.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.softhink.single.base.BaseCallback
import com.softhink.single.GenericObserver
import com.softhink.single.SingleLiveEvent
import com.softhink.single.SingleRepository
import com.softhink.single.models.request.LoginRequest
import com.softhink.single.registro.Status

class LoginViewModel : ViewModel() {

    private var repository: SingleRepository = SingleRepository()
    private var login = SingleLiveEvent<GenericObserver<String>>()
    private val request = LoginRequest()

    fun login(user: String, pss: String): LiveData<GenericObserver<String>> {
        request.username = user
        request.password = pss
        if (validateData()){
            return makeRequest()
        }
        return login
    }

    private fun makeRequest() : LiveData<GenericObserver<String>> {
        repository.callLogin(request, object : BaseCallback<String>() {
            override fun handleResponseData(data: String) {
                login.value = GenericObserver(Status.SUCCESS, data)
            }

            override fun handleError(error: String?) {
                login.value = GenericObserver(Status.ERROR, "error")
            }

            override fun handleException(t: Exception) {
                login.value = GenericObserver(Status.FAILED, t.message!!)
            }
        })

        return login
    }

    private fun validateData(): Boolean{
        if (request.username.isNullOrBlank()){
            login.value = GenericObserver(Status.ERROR, "Ingresa tu correo y contraeña para ingresar")
            return false
        }
        if (request.password.isNullOrBlank()){
            login.value = GenericObserver(Status.ERROR, "Ingresa tu correo y contraeña para ingresar")
            return false
        }

        return true
    }
}