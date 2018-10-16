package com.softhink.single.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.softhink.single.BaseCallback
import com.softhink.single.SingleRepository
import com.softhink.single.models.request.LoginRequest
import com.softhink.single.models.response.BaseResponse
import com.softhink.single.models.response.LoginResponse

class LoginViewModel : ViewModel() {

    private var repository: SingleRepository = SingleRepository()
    private var dataLogin = MutableLiveData<LoginResponse>()

    fun login(user: String, pss: String): LiveData<LoginResponse> {
        return makeRequest(user, pss)
    }


    fun makeRequest(user: String, pss: String) : LiveData<LoginResponse> {
        repository.callLogin(LoginRequest(pss, user), object : BaseCallback<LoginResponse>() {
            override fun handleResponseData(data: LoginResponse) {
                dataLogin.value = data
            }

            override fun handleError(response: BaseResponse<LoginResponse>) {
                val mutableData = MutableLiveData<LoginResponse>()
                mutableData.value = response.error
            }

            override fun handleException(t: Exception) {
            }
        })

        return dataLogin
    }

}