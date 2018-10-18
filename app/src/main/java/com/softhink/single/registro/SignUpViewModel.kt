package com.softhink.single.registro

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.softhink.single.BaseCallback
import com.softhink.single.GenericObserver
import com.softhink.single.R
import com.softhink.single.SingleRepository
import com.softhink.single.models.request.RegRequest
import com.softhink.single.models.response.BaseResponse
import java.text.SimpleDateFormat
import java.util.*

class SignUpViewModel: ViewModel() {

    private var repository = SingleRepository()
    private lateinit var statusForm: MutableLiveData<SignUpObserver>
    private var responseRepository = MutableLiveData<GenericObserver<Any>>()

    private lateinit var name: String
    private lateinit var date: String
    private lateinit var gender: String
    private lateinit var email: String
    private lateinit var pss: String
    private lateinit var imageProfile: String

    fun validateForm(name: String, date: Date?, gender: String?): LiveData<SignUpObserver>{
        if (isValidName(name) && isValidDate(date) && genderSelected(gender)) {
            statusForm = MutableLiveData()
            statusForm.value = SignUpObserver(Status.SUCCESS, R.string.signup_succes)
            this.name = name
            this.date = SimpleDateFormat("yyyy-MM-dd", Locale("ES")).format(date)
            this.gender = gender!!
        }
        return statusForm
    }

    fun accountData(email: String, pss: String, pss2: String):LiveData<SignUpObserver> {
        if(isValidEmail(email) && passwordMatch(pss, pss2)){
            statusForm = MutableLiveData()
            statusForm.value = SignUpObserver(Status.SUCCESS, R.string.signup_succes)
            this.email = email
            this.pss = pss
        }

        return statusForm
    }


    private fun isValidName(string: String): Boolean {
        if(string.isEmpty()){
            statusForm = MutableLiveData()
            statusForm.value = SignUpObserver(Status.ERROR, R.string.signup_empty_name)
            return false
        } else if (string.length < 3) {
            statusForm = MutableLiveData()
            statusForm.value = SignUpObserver(Status.ERROR,R.string.signup_invalid_name)
            return false
        }
        return true
    }

    private fun isValidDate(date: Date?): Boolean{
        val cal = Calendar.getInstance(Locale("es"))
        cal.add(Calendar.YEAR, -18)

        if(date == null) {
            statusForm = MutableLiveData()
            statusForm.value = SignUpObserver(Status.ERROR, R.string.signup_birthday_null)
            return false
        }else if (date.after(cal.time)){
            statusForm = MutableLiveData()
            statusForm.value = SignUpObserver(Status.ERROR, R.string.signup_under_age)
            return false
        }
        return true
    }

    private fun genderSelected(gender: String?) : Boolean {
        if(gender == null){
            statusForm = MutableLiveData()
            statusForm.value = SignUpObserver(Status.ERROR, R.string.signup_missing_gender)
            return false
        }
        return true
    }

    private fun isValidEmail(mail: String?) : Boolean {
        if (mail?.isEmpty()!!){
            statusForm = MutableLiveData()
            statusForm.value = SignUpObserver(Status.ERROR, R.string.signup_missing_email)
            return false
        } else if(Patterns.EMAIL_ADDRESS.matcher(mail).matches()) {
            return true
        }

        statusForm = MutableLiveData()
        statusForm.value = SignUpObserver(Status.ERROR, R.string.signup_invalid_email)
        return false
    }

    private fun passwordMatch(pss: String?, pss2: String?) : Boolean {
        if (pss?.isEmpty()!! || pss2?.isEmpty()!!) {
            statusForm = MutableLiveData()
            statusForm.value = SignUpObserver(Status.ERROR, R.string.signup_missing_pss)
            return false
        } else if (pss.length < 8 || pss2.length < 8){
            statusForm = MutableLiveData()
            statusForm.value = SignUpObserver(Status.ERROR, R.string.signup_pss_lenght)
            return false
        } else if (pss == pss2){
            return true
        }

        statusForm = MutableLiveData()
        statusForm.value = SignUpObserver(Status.ERROR, R.string.signup_pss_match)
        return false
    }

    fun callSignUpService() : LiveData<GenericObserver<Any>> {
        repository.callRegistro(RegRequest(name, date, gender, email, pss, null), object :BaseCallback<GenericObserver<Any>>(){
            override fun handleResponseData(data: GenericObserver<Any>) {

            }

            override fun handleError(response: BaseResponse<GenericObserver<Any>>) {

            }

            override fun handleException(t: Exception) {
                responseRepository.value = GenericObserver(Status.FAILED, t)
            }
        })

        return responseRepository
    }
}