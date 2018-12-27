package com.softhink.single.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.softhink.single.base.BaseCallback
import com.softhink.single.GenericObserver
import com.softhink.single.R
import com.softhink.single.SingleLiveEvent
import com.softhink.single.SingleRepository
import com.softhink.single.base.SingleApplication
import com.softhink.single.models.request.LoginRequest
import com.softhink.single.models.response.UserResponse
import com.softhink.single.ui.registro.Status

class LoginViewModel : ViewModel() {

    private var repository: SingleRepository = SingleRepository()
    private var login = SingleLiveEvent<GenericObserver<UserResponse>>()
    private val request = LoginRequest()

    fun login(user: String, pss: String): LiveData<GenericObserver<UserResponse>> {
        request.username = user
        request.password = pss
        if (validateData()){
            return makeRequest()
        }
        return login
    }

    private fun makeRequest() : LiveData<GenericObserver<UserResponse>> {
        repository.callLogin(request, object : BaseCallback<UserResponse>() {
            override fun handleResponseData(data: UserResponse, message: String?) {
                login.value = GenericObserver(Status.SUCCESS, data, message)
            }

            override fun handleError(message: String) {
                login.value = GenericObserver(Status.ERROR, null, message)
            }

            override fun handleException(t: Exception) {
                login.value = GenericObserver(Status.FAILED, null,
                        SingleApplication.applicationContext().getString(R.string.error_generic_message))
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