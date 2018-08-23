package com.softhink.single.registro.presenter

import com.softhink.single.BasePresenter
import com.softhink.single.Interactor
import com.softhink.single.models.request.LoginRequest
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class RegDataPresenter(private val view: RegDataContract) : BasePresenter() {

    init {
        interactor = Interactor(this)
        interactor.callLogin(LoginRequest("pss", "text@mail.com"))
    }

    fun validateForm(name: String, date: Date?, gender: String?) {
        if (validateName(name) && validDate(date) && gender != null) {
            view.toNextFragment()
        }
    }

    fun validateName(string: String): Boolean {
        if(string.isEmpty()){
            view.nameIsEmpty()
            return false
        } else if (string.length < 3){
            view.nameStringLenght()
            return false
        }
        return true
    }

    fun validDate(date: Date?): Boolean{
        val cal = Calendar.getInstance()
        cal.add(Calendar.YEAR, -18)

        if(date == null) {
            view.dateEmpty()
            return false
        }else if (date.after(cal.time)){
            view.isUnderAge()
            return false
        }
        return true
    }

    fun getDateForPicker(s1: String, s2: String, s3: String) : Array<Int> {
        if (s1.isEmpty() && s2.isEmpty() && s3.isEmpty()){
            return arrayOf(Calendar.getInstance().get(Calendar.YEAR),
                    Calendar.getInstance().get(Calendar.MONTH),
                    Calendar.getInstance().get(Calendar.DAY_OF_MONTH))
        } else {
            return arrayOf(s1.toInt(), s2.toInt() - 1, s3.toInt())
        }
    }

    fun getUserBirthDay(year: Int, month: Int, dayOfMonth: Int):Date {
        val date = SimpleDateFormat("yyyy-MM-dd")
        return date.parse("$year-${month+1}-$dayOfMonth")
    }

    override fun onResponseSuccess(t: Any) {
        println(t)
    }

    override fun onResponseError(t: Any) {

    }

    override fun onFailed() {

    }
}