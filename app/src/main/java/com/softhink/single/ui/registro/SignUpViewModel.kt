package com.softhink.single.ui.registro

import android.util.Patterns
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
        if (isValidName(name) && isValidDate(date) && genderSelected(gender)) {
            userRequest.fullName = name
            userRequest.birthdate = SimpleDateFormat("dd-MM-yyyy", Locale("ES")).format(date)
            userRequest.sex = gender!!
            statusForm.value = GenericObserver(Status.SUCCESS, null, getString(R.string.signup_complete))
        }
        return statusForm
    }

    fun accountData(email: String, pss: String, pss2: String): LiveData<GenericObserver<Any>> {
        statusForm = SingleLiveEvent()
        if(isValidEmail(email) && passwordMatch(pss, pss2)){
            statusForm.value = GenericObserver(Status.SUCCESS, null, getString(R.string.signup_complete))
            userRequest.email = email
            userRequest.password = pss
        }

        return statusForm
    }


    private fun isValidName(string: String): Boolean {
        if(string.isEmpty()){
            statusForm.value = GenericObserver(Status.ERROR, null, getString(R.string.signup_empty_name))
            return false
        } else if (string.length < 3) {
            statusForm.value = GenericObserver(Status.ERROR, null, getString(R.string.signup_invalid_name))
            return false
        }
        return true
    }

    private fun isValidDate(date: Date?): Boolean{
        val cal = Calendar.getInstance(Locale("es"))
        cal.add(Calendar.YEAR, -18)

        if(date == null) {
            statusForm.value = GenericObserver(Status.ERROR, null, getString(R.string.signup_birthday_null))
            return false
        }else if (date.after(cal.time)){
            statusForm.value = GenericObserver(Status.ERROR, null, getString(R.string.signup_under_age))
            return false
        }
        return true
    }

    private fun genderSelected(gender: String?) : Boolean {
        if(gender == null){
            statusForm.value = GenericObserver(Status.ERROR, null, getString(R.string.signup_missing_gender))
            return false
        }
        return true
    }

    private fun isValidEmail(mail: String?) : Boolean {
        if (mail?.isEmpty()!!){
            statusForm.value = GenericObserver(Status.ERROR, null, getString(R.string.signup_missing_email))
            return false
        } else if(Patterns.EMAIL_ADDRESS.matcher(mail).matches()) {
            return true
        }

        statusForm.value = GenericObserver(Status.ERROR, null, getString(R.string.signup_invalid_email))
        return false
    }

    private fun passwordMatch(pss: String?, pss2: String?) : Boolean {
        if (pss?.isEmpty()!! || pss2?.isEmpty()!!) {
            statusForm.value = GenericObserver(Status.ERROR, null, getString(R.string.signup_missing_pss))
            return false
        } else if (pss.length < 8 || pss2.length < 8){
            statusForm.value = GenericObserver(Status.ERROR, null, getString(R.string.signup_pss_lenght))
            return false
        } else if (pss == pss2){
            return true
        }

        statusForm.value = GenericObserver(Status.ERROR, null, getString(R.string.signup_pss_match))
        return false
    }

    fun callSignUpService() : LiveData<GenericObserver<UserResponse>> {
        scope.launch {
            responseRepository.postValue(repository.makeRequest(SignUpRequest(userRequest)))
        }
        return responseRepository
    }
}