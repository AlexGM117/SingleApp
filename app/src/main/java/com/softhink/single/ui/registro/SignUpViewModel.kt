package com.softhink.single.ui.registro

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.softhink.single.*
import com.softhink.single.base.BaseCallback
import com.softhink.single.base.BaseViewModel
import com.softhink.single.models.request.RegistroRequest
import com.softhink.single.models.response.UserResponse
import java.text.SimpleDateFormat
import java.util.*

class SignUpViewModel: BaseViewModel() {

    private var repository = SingleRepository()
    private var statusForm = SingleLiveEvent<GenericObserver<Any>>()
    private var responseRepository = MutableLiveData<GenericObserver<UserResponse>>()
    private var request = RegistroRequest()

    fun validateForm(name: String, date: Date?, gender: String?): LiveData<GenericObserver<Any>>{
        if (isValidName(name) && isValidDate(date) && genderSelected(gender)) {
            request.fullName = name
            request.birthdate = SimpleDateFormat("yyyy-MM-dd", Locale("ES")).format(date)
            request.sex = gender!!
            statusForm.value = GenericObserver(Status.SUCCESS, null, getString(R.string.signup_complete))
        }
        return statusForm
    }

    fun accountData(email: String, pss: String, pss2: String): LiveData<GenericObserver<Any>> {
        statusForm = SingleLiveEvent<GenericObserver<Any>>()
        if(isValidEmail(email) && passwordMatch(pss, pss2)){
            statusForm.value = GenericObserver(Status.SUCCESS, null, getString(R.string.signup_complete))
            request.email = email
            request.password = pss
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
        repository.callRegistro(request, object : BaseCallback<UserResponse>(){
            override fun handleResponseData(data: UserResponse, message: String?) {
                responseRepository.value = GenericObserver(Status.SUCCESS, data, message)
            }

            override fun handleError(message: String) {
                responseRepository.value = GenericObserver(Status.ERROR, null, message)
            }

            override fun handleException(t: Exception) {
                responseRepository.value = GenericObserver(Status.FAILED, null, t.message)
            }
        })

        return responseRepository
    }
}