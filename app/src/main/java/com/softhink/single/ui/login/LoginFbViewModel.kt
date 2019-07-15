package com.softhink.single.ui.login

import android.content.Intent
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import com.facebook.*
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.softhink.single.data.manager.GenericObserver
import com.softhink.single.data.manager.SingleLiveEvent
import com.softhink.single.data.remote.response.UserResponse
import com.softhink.single.ui.base.BaseViewModel
import com.softhink.single.ui.registro.Status
import java.util.*

class LoginFbViewModel : BaseViewModel() {

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
                        //TODO Make Login or SignUp request
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