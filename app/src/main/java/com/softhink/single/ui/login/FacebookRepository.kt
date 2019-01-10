package com.softhink.single.ui.login

import android.os.Bundle
import androidx.lifecycle.LiveData
import com.facebook.AccessToken
import com.facebook.GraphRequest
import com.google.gson.Gson
import com.softhink.single.data.manager.GenericObserver
import com.softhink.single.R
import com.softhink.single.data.manager.SingleLiveEvent
import com.softhink.single.data.manager.SingleRepository
import com.softhink.single.ui.base.BaseCallback
import com.softhink.single.SingleApplication
import com.softhink.single.data.remote.request.LoginRequest
import com.softhink.single.data.remote.request.RegistroRequest
import com.softhink.single.data.remote.response.FacebookResponse
import com.softhink.single.data.remote.response.UserResponse
import com.softhink.single.ui.registro.Status

class FacebookRepository : BaseCallback<UserResponse>() {

    private val repository: SingleRepository = SingleRepository()
    private var userResponse = SingleLiveEvent<GenericObserver<UserResponse>>()
    private lateinit var facebookResponse : FacebookResponse

    fun makeRequest(accessToken: AccessToken) : LiveData<GenericObserver<UserResponse>> {
        val request = GraphRequest.newMeRequest(accessToken) { `object`, response ->
            if (response?.error == null) {
                facebookResponse = Gson().fromJson(response.rawResponse.toString(), FacebookResponse::class.java)
                repository.callLogin(LoginRequest(facebookResponse.email, facebookResponse.id), this)
            } else {
                userResponse.value = GenericObserver(Status.ERROR, null, response.error.errorMessage)
            }
        }
        val parameters = Bundle()
        parameters.putString("fields", "id, name, birthday, email, gender, first_name, last_name")
        request.parameters = parameters
        request.executeAsync()

        return userResponse
    }

    override fun handleResponseData(data: UserResponse, message: String?) {
        userResponse.value = GenericObserver(Status.SUCCESS, data, message)
    }

    override fun handleError(message: String, resultCode: String?) {
        if (resultCode == "202"){
            repository.callRegistro(RegistroRequest(facebookResponse.name, facebookResponse.birthday,
                    facebookResponse.gender, facebookResponse.email, facebookResponse.id, null), this)
        } else {
            userResponse.value = GenericObserver(Status.ERROR, null, message)
        }
    }

    override fun handleException(t: Exception) {
        userResponse.value = GenericObserver(Status.FAILED, null,
                SingleApplication.applicationContext().getString(R.string.error_generic_message))
    }
}