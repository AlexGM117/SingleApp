package com.softhink.single.registro.presenter

import android.util.Patterns
import com.softhink.single.BasePresenter
import com.softhink.single.Interactor
import com.softhink.single.models.request.RegRequest
import java.text.SimpleDateFormat
import java.util.*

class RegistroPresenter(private var viewData: RegistroContract.DataContract,
                        private var viewAccount: RegistroContract.AccountContract): BasePresenter() {

    private lateinit var request: RegRequest

    init {
        interactor = Interactor(this)
    }

    fun validateForm(name: String, date: Date?, gender: String?) {
        if (validateName(name) && validDate(date) && genderSelected(gender)) {
            viewData.toAccountFragment()
            request = RegRequest(name, SimpleDateFormat("yyyy-MM-dd", Locale("ES")).format(date), gender!!)
        }
    }

    private fun genderSelected(gender: String?) : Boolean {
        if(gender == null){
            viewData.genderUnselected()
            return false
        }
        return true
    }

    private fun validateName(string: String): Boolean {
        if(string.isEmpty()){
            viewData.nameIsEmpty()
            return false
        } else if (string.length < 3) {
            viewData.nameStringLenght()
            return false
        }
        return true
    }

    private fun validDate(date: Date?): Boolean{
        val cal = Calendar.getInstance(Locale("es"))
        cal.add(Calendar.YEAR, -18)

        if(date == null) {
            viewData.dateEmpty()
            return false
        }else if (date.after(cal.time)){
            viewData.isUnderAge()
            return false
        }
        return true
    }

    fun validAccountData(email: String?, pss: String?, pss2: String?){
        if (validEmail(email) && passwordMatch(pss, pss2)){
            viewAccount.finishRegistro()
            request.email = email!!
            request.password = pss!!
        }
    }

    private fun validEmail(mail: String?) : Boolean {
        if (mail?.isEmpty()!!){
            viewAccount.emailEmpty()
            return false
        } else if(Patterns.EMAIL_ADDRESS.matcher(mail).matches()) {
            return true
        }

        viewAccount.invalidEmail()
        return false
    }

    private fun passwordMatch(pss: String?, pss2: String?) : Boolean {
        if (pss?.isEmpty()!! || pss2?.isEmpty()!!) {
            viewAccount.passEmpty()
            return false
        } else if (pss.length < 8 || pss2.length < 8){
            viewAccount.passLenght()
            return false
        } else if (pss == pss2){
            return true
        }

        viewAccount.passNotMatch()
        return false
    }

    fun sendRegistro(photo: String?) {
        if (photo != null) request.imageProfile = photo
        interactor.callRegistro(request)
    }

    override fun onResponseSuccess(t: Any) {

    }

    override fun onResponseError(t: Any) {

    }

    override fun onFailed() {

    }
}