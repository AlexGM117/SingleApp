package com.softhink.single.ui.dashboard

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.softhink.single.R
import com.softhink.single.SingleApplication
import com.softhink.single.data.manager.GenericObserver
import com.softhink.single.data.manager.SingleRepository
import com.softhink.single.data.remote.request.UserTest
import com.softhink.single.data.remote.response.UserResponse
import com.softhink.single.ui.base.BaseCallback
import com.softhink.single.ui.registro.Status

class MapViewModel: ViewModel() {

    private var repository: SingleRepository = SingleRepository()
    private var usersLiveData = MutableLiveData<GenericObserver<List<UserResponse>>>()

    fun getListOfUsers() {
        repository.callGetUsers(UserTest("test"), object : BaseCallback<List<UserResponse>>(){
            override fun handleResponseData(data: List<UserResponse>, message: String?) {
                if (data.isNullOrEmpty()){
                    usersLiveData.value = GenericObserver(Status.ERROR, null,
                            SingleApplication.applicationContext().getString(R.string.error_generic_message))
                } else {
                    usersLiveData.value = GenericObserver(Status.SUCCESS, data, null)
                }
            }

            override fun handleError(message: String, resultCode: String?) {
                usersLiveData.value = GenericObserver(Status.ERROR, null, message)
            }

            override fun handleException(t: Exception) {
                usersLiveData.value = GenericObserver(Status.FAILED, null, t.message)
            }
        })
    }

    fun getListResponse() : MutableLiveData<GenericObserver<List<UserResponse>>> {
        return usersLiveData
    }
}