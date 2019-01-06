package com.softhink.single.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.facebook.*
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.gson.Gson
import com.softhink.single.GenericObserver
import com.softhink.single.R
import com.softhink.single.SingleLiveEvent
import com.softhink.single.SingleRepository
import com.softhink.single.base.BaseCallback
import com.softhink.single.base.SingleApplication
import com.softhink.single.models.request.LoginRequest
import com.softhink.single.models.request.RegistroRequest
import com.softhink.single.models.response.FacebookResponse
import com.softhink.single.models.response.UserResponse
import com.softhink.single.ui.registro.Status
import java.util.*

class LoginFbViewModel : ViewModel() {

    private var repository: SingleRepository = SingleRepository()
    private var callbackManager: CallbackManager = CallbackManager.Factory.create()
    private var loginResponse = SingleLiveEvent<GenericObserver<UserResponse>>()

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?){
        callbackManager.onActivityResult(requestCode, resultCode, data)
    }

    fun fbLogin(fragment: Fragment) : LiveData<GenericObserver<UserResponse>> {
        LoginManager.getInstance().registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(result: LoginResult?) {
                if (result != null) {
                    if (result.accessToken.declinedPermissions.size == 0) {
                        val request = GraphRequest.newMeRequest(result.accessToken) { `object`, response ->
                            if (response?.error == null) {
                                val facebookResponse = Gson().fromJson(response.rawResponse.toString(), FacebookResponse::class.java)

                                repository.callLogin(LoginRequest(facebookResponse.email, facebookResponse.id), object : BaseCallback<UserResponse>() {
                                    override fun handleResponseData(data: UserResponse, message: String?) {
                                        loginResponse.value = GenericObserver(Status.SUCCESS, data, message)
                                    }

                                    override fun handleError(message: String, resultCode: String?) {
                                        if (resultCode == "202"){
                                            repository.callRegistro(RegistroRequest(facebookResponse.name, facebookResponse.birthday, facebookResponse.gender, facebookResponse.email, facebookResponse.id, null),
                                                    object : BaseCallback<UserResponse>(){
                                                        override fun handleResponseData(data: UserResponse, message: String?) {
                                                            loginResponse.value = GenericObserver(Status.SUCCESS, data, message)
                                                        }

                                                        override fun handleError(message: String, resultCode: String?) {
                                                            loginResponse.value = GenericObserver(Status.ERROR, null, message)
                                                        }

                                                        override fun handleException(t: Exception) {
                                                            loginResponse.value = GenericObserver(Status.FAILED, null,
                                                                    SingleApplication.applicationContext().getString(R.string.error_generic_message))
                                                        }
                                                    })
                                        } else {
                                            loginResponse.value = GenericObserver(Status.ERROR, null, message)
                                        }
                                    }

                                    override fun handleException(t: Exception) {
                                        loginResponse.value = GenericObserver(Status.FAILED, null,
                                                SingleApplication.applicationContext().getString(R.string.error_generic_message))
                                    }
                                })
                            } else {
                                loginResponse.value = GenericObserver(Status.ERROR, null, response.error.errorMessage)
                            }
                        }
                        val parameters = Bundle()
                        parameters.putString("fields", "id, name, birthday, email, gender, first_name, last_name")
                        request.parameters = parameters
                        request.executeAsync()
                    } else {
                        loginResponse.value = GenericObserver(Status.ERROR, null, "El usuario no acepto los permisos")
                    }
                }
            }

            override fun onCancel() {
                loginResponse.value = GenericObserver(Status.ERROR, null, "El usuario cancelo ")
            }

            override fun onError(error: FacebookException?) {
                loginResponse.value = GenericObserver(Status.ERROR, null, error?.message)
            }
        })

        LoginManager.getInstance().logInWithReadPermissions(fragment, Arrays.asList("public_profile", "user_birthday", "user_gender", "email"))

        return loginResponse
    }
}