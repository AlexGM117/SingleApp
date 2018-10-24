package com.softhink.single.ui.registro

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.softhink.single.*
import com.softhink.single.base.BaseCallback
import com.softhink.single.models.request.RegRequest
import java.text.SimpleDateFormat
import java.util.*

class SignUpViewModel: ViewModel() {

    private var repository = SingleRepository()
    private var statusForm = SingleLiveEvent<GenericObserver<Int>>()
    private var responseRepository = MutableLiveData<GenericObserver<Any>>()
    private var request = RegRequest()

    fun validateForm(name: String, date: Date?, gender: String?): LiveData<GenericObserver<Int>>{
        if (isValidName(name) && isValidDate(date) && genderSelected(gender)) {
            statusForm.value = GenericObserver(Status.SUCCESS, R.string.signup_succes)
            request.name = name
            request.date = SimpleDateFormat("yyyy-MM-dd", Locale("ES")).format(date)
            request.gender = gender!!
        }
        return statusForm
    }

    fun accountData(email: String, pss: String, pss2: String): LiveData<GenericObserver<Int>> {
        statusForm = SingleLiveEvent<GenericObserver<Int>>()
        if(isValidEmail(email) && passwordMatch(pss, pss2)){
            statusForm.value = GenericObserver(Status.SUCCESS, R.string.signup_succes)
            request.email = email
            request.pss = pss
        }

        return statusForm
    }


    private fun isValidName(string: String): Boolean {
        if(string.isEmpty()){
            statusForm.value = GenericObserver(Status.ERROR, R.string.signup_empty_name)
            return false
        } else if (string.length < 3) {
            statusForm.value = GenericObserver(Status.ERROR,R.string.signup_invalid_name)
            return false
        }
        return true
    }

    private fun isValidDate(date: Date?): Boolean{
        val cal = Calendar.getInstance(Locale("es"))
        cal.add(Calendar.YEAR, -18)

        if(date == null) {
            statusForm.value = GenericObserver(Status.ERROR, R.string.signup_birthday_null)
            return false
        }else if (date.after(cal.time)){
            statusForm.value = GenericObserver(Status.ERROR, R.string.signup_under_age)
            return false
        }
        return true
    }

    private fun genderSelected(gender: String?) : Boolean {
        if(gender == null){
            statusForm.value = GenericObserver(Status.ERROR, R.string.signup_missing_gender)
            return false
        }
        return true
    }

    private fun isValidEmail(mail: String?) : Boolean {
        if (mail?.isEmpty()!!){
            statusForm.value = GenericObserver(Status.ERROR, R.string.signup_missing_email)
            return false
        } else if(Patterns.EMAIL_ADDRESS.matcher(mail).matches()) {
            return true
        }

        statusForm.value = GenericObserver(Status.ERROR, R.string.signup_invalid_email)
        return false
    }

    private fun passwordMatch(pss: String?, pss2: String?) : Boolean {
        if (pss?.isEmpty()!! || pss2?.isEmpty()!!) {
            statusForm.value = GenericObserver(Status.ERROR, R.string.signup_missing_pss)
            return false
        } else if (pss.length < 8 || pss2.length < 8){
            statusForm.value = GenericObserver(Status.ERROR, R.string.signup_pss_lenght)
            return false
        } else if (pss == pss2){
            return true
        }

        statusForm.value = GenericObserver(Status.ERROR, R.string.signup_pss_match)
        return false
    }

    fun callSignUpService() : LiveData<GenericObserver<Any>> {
        repository.callRegistro(request, object : BaseCallback<GenericObserver<Any>>(){
            override fun handleResponseData(data: GenericObserver<Any>) {

            }

            override fun handleError(error: GenericObserver<Any>?) {

            }

            override fun handleException(t: Exception) {
                responseRepository.value = GenericObserver(Status.FAILED, t)
            }
        })

        return responseRepository
    }
}