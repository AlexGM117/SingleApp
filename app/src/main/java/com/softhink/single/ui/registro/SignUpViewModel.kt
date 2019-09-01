package com.softhink.single.ui.registro

import androidx.lifecycle.LiveData
import com.softhink.single.*
import com.softhink.single.data.manager.GenericObserver
import com.softhink.single.data.manager.SingleLiveEvent
import com.softhink.single.ui.base.BaseViewModel
import com.softhink.single.data.remote.request.SignUpRequest
import com.softhink.single.data.remote.request.UserRequest
import com.softhink.single.data.remote.response.UserResponse
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class SignUpViewModel: BaseViewModel() {

    private var statusForm = SingleLiveEvent<GenericObserver<Any>>()
    private var responseRepository = SingleLiveEvent<GenericObserver<UserResponse>>()
    private var userRequest = UserRequest()

    fun validateForm(name: String, date: Date?, gender: String?): LiveData<GenericObserver<Any>>{
        if (isValidName(name, statusForm) && isValidDate(date, statusForm) && genderSelected(gender, statusForm)) {
            userRequest.fullName = name
            userRequest.birthdate = SimpleDateFormat("dd-MM-yyyy", Locale("ES")).format(date)
            userRequest.sex = gender!!
            statusForm.value = GenericObserver(Status.SUCCESS, null, getString(R.string.signup_complete))
        }
        return statusForm
    }

    fun accountData(email: String, pss: String, pss2: String): LiveData<GenericObserver<Any>> {
        statusForm = SingleLiveEvent()
        if(isValidEmail(email, statusForm) && passwordMatch(pss, pss2, statusForm)){
            statusForm.value = GenericObserver(Status.SUCCESS, null, getString(R.string.signup_complete))
            userRequest.email = email
            userRequest.password = pss
        }

        return statusForm
    }

    fun setPhoto(base64Encode: String) {
        println("Base 64: $base64Encode")
        userRequest.imageProfile = base64Encode
    }

    private fun genderSelected(gender: String?, statusForm: SingleLiveEvent<GenericObserver<Any>>) : Boolean {
        if(gender == null){
            this.statusForm.value = GenericObserver(Status.ERROR, null, getString(R.string.signup_missing_gender))
            return false
        }
        return true
    }


    fun callSignUpService() : LiveData<GenericObserver<UserResponse>> {
        scope.launch {
            responseRepository.postValue(repository.makeRequest(SignUpRequest(userRequest)))
        }
        return responseRepository
    }
}