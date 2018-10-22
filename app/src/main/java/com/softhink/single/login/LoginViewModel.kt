package com.softhink.single.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.softhink.single.BaseCallback
import com.softhink.single.GenericObserver
import com.softhink.single.SingleLiveEvent
import com.softhink.single.SingleRepository
import com.softhink.single.models.request.LoginRequest
import com.softhink.single.registro.Status

class LoginViewModel : ViewModel() {

    private var repository: SingleRepository = SingleRepository()
    private var dataLogin = SingleLiveEvent<GenericObserver<String>>()
    private val request = LoginRequest()

    fun login(user: String, pss: String): LiveData<GenericObserver<String>> {
        request.username = user
        request.password = pss
        return makeRequest()
    }

    fun makeRequest() : LiveData<GenericObserver<String>> {
        repository.callLogin(request, object : BaseCallback<String>() {
            override fun handleResponseData(data: String) {
                dataLogin.value = GenericObserver(Status.SUCCESS, data)
            }

            override fun handleError(error: String?) {
                dataLogin.value = GenericObserver(Status.ERROR, "error")
            }

            override fun handleException(t: Exception) {
                dataLogin.value = GenericObserver(Status.FAILED, t.message!!)
            }
        })

        return dataLogin
    }

}