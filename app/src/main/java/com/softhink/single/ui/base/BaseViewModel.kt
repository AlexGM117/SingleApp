package com.softhink.single.ui.base

import android.util.Patterns
import androidx.lifecycle.ViewModel
import com.softhink.single.R
import com.softhink.single.SingleApplication
import com.softhink.single.SingleData
import com.softhink.single.SingleRepository
import com.softhink.single.api.SingleRoomDatabase
import com.softhink.single.data.manager.ApiRepository
import com.softhink.single.data.manager.GenericObserver
import com.softhink.single.data.manager.SingleLiveEvent
import com.softhink.single.data.remote.response.UserResponse
import com.softhink.single.ui.registro.Status
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.*
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel : ViewModel() {

    private val parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Default
    protected val scope = CoroutineScope(coroutineContext)
    protected val repository = ApiRepository()
    protected val roomRepository = SingleRepository(SingleRoomDatabase.getDatabase(SingleApplication.applicationContext(), scope).singleDataDAO())

    fun getString(string: Int): String {
        return SingleApplication.applicationContext().getString(string)
    }

    protected fun isValidEmail(mail: String?, observer: SingleLiveEvent<GenericObserver<Any>>) : Boolean {
        if (mail?.isEmpty()!!){
            observer.value = GenericObserver(Status.ERROR, null, getString(R.string.signup_missing_email))
            return false
        } else if(Patterns.EMAIL_ADDRESS.matcher(mail).matches()) {
            return true
        }

        observer.value = GenericObserver(Status.ERROR, null, getString(R.string.signup_invalid_email))
        return false
    }

    protected fun passwordMatch(pss: String?, pss2: String?, observer: SingleLiveEvent<GenericObserver<Any>>) : Boolean {
        if (pss?.isEmpty()!! || pss2?.isEmpty()!!) {
            observer.value = GenericObserver(Status.ERROR, null, getString(R.string.signup_missing_pss))
            return false
        } else if (pss.length < 8 || pss2.length < 8){
            observer.value = GenericObserver(Status.ERROR, null, getString(R.string.signup_pss_lenght))
            return false
        } else if (pss == pss2){
            return true
        }

        observer.value = GenericObserver(Status.ERROR, null, getString(R.string.signup_pss_match))
        return false
    }

    protected fun isValidName(string: String, observer: SingleLiveEvent<GenericObserver<Any>>): Boolean {
        if(string.isEmpty()){
            observer.value = GenericObserver(Status.ERROR, null, getString(R.string.signup_empty_name))
            return false
        } else if (string.length < 3) {
            observer.value = GenericObserver(Status.ERROR, null, getString(R.string.signup_invalid_name))
            return false
        }
        return true
    }

    protected fun isValidDate(date: Date?, observer: SingleLiveEvent<GenericObserver<Any>>): Boolean{
        val cal = Calendar.getInstance(Locale("es"))
        cal.add(Calendar.YEAR, -18)

        if(date == null) {
            observer.value = GenericObserver(Status.ERROR, null, getString(R.string.signup_birthday_null))
            return false
        }else if (date.after(cal.time)){
            observer.value = GenericObserver(Status.ERROR, null, getString(R.string.signup_under_age))
            return false
        }
        return true
    }

    fun saveLocalData(data: UserResponse) = scope.launch {
        val dataInsert = SingleData(data.username,
                data.fullName,
                null,
                data.birthDate,
                data.email,
                null,
                null,
                data.fechaAlta,
                null)
        roomRepository.insert(dataInsert)
    }

    fun restoreData() = scope.launch {
        roomRepository.truncate()
    }
}