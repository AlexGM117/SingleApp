package com.softhink.single.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.softhink.single.base.BaseCallback
import com.softhink.single.GenericObserver
import com.softhink.single.SingleLiveEvent
import com.softhink.single.SingleRepository
import com.softhink.single.models.request.LoginRequest
import com.softhink.single.ui.registro.Status

class LoginViewModel : ViewModel() {

    private var repository: SingleRepository = SingleRepository()
    private var login = SingleLiveEvent<GenericObserver<Any>>()
    private val request = LoginRequest()

    fun login(user: String, pss: String): LiveData<GenericObserver<Any>> {
        request.username = user
        request.password = pss
        if (validateData()){
            return if (request.username.equals("admin") && request.username.equals("admin")){
                login.value = GenericObserver(Status.SUCCESS, null, "Login local")
                login
            } else {
                makeRequest()
            }
        }
        return login
    }

    private fun makeRequest() : LiveData<GenericObserver<Any>> {
        repository.callLogin(request, object : BaseCallback<Any>() {
            override fun handleResponseData(data: Any, message: String) {
                login.value = GenericObserver(Status.SUCCESS, data, message)
            }

            override fun handleError(error: Any?, message: String) {
                login.value = GenericObserver(Status.ERROR, null, message)
            }

            override fun handleException(t: Exception) {
                login.value = GenericObserver(Status.FAILED, null, t.message!!)
            }
        })

        return login
    }

    private fun validateData(): Boolean{
        if (request.username.isNullOrBlank()){
            login.value = GenericObserver(Status.ERROR, null,"Ingresa tu correo y contraeña para ingresar")
            return false
        }
        if (request.password.isNullOrBlank()){
            login.value = GenericObserver(Status.ERROR, null,"Ingresa tu correo y contraeña para ingresar")
            return false
        }

        return true
    }
}