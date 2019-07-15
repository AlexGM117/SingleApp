package com.softhink.single.ui.login

import androidx.lifecycle.LiveData
import com.softhink.single.data.manager.GenericObserver
import com.softhink.single.data.manager.SingleLiveEvent
import com.softhink.single.data.remote.request.LoginRequest
import com.softhink.single.data.remote.response.UserResponse
import com.softhink.single.ui.base.BaseViewModel
import com.softhink.single.ui.registro.Status
import kotlinx.coroutines.launch

class LoginViewModel : BaseViewModel() {

//    private var login = SingleLiveEvent<GenericObserver<UserResponse>>()
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
        scope.launch {
            login.postValue(repository.makeRequest(request))
        }

        return login
    }

    private fun validateData(): Boolean{
        if (request.username.isNullOrBlank()){
            login.value = GenericObserver(Status.ERROR, null, "Ingresa tu correo y contraeña para ingresar")
            return false
        }
        if (request.password.isNullOrBlank()){
            login.value = GenericObserver(Status.ERROR, null, "Ingresa tu correo y contraeña para ingresar")
            return false
        }

        return true
    }
}