package com.softhink.single.ui.dashboard

import androidx.lifecycle.MutableLiveData
import com.softhink.single.data.manager.GenericObserver
import com.softhink.single.data.remote.request.UserTest
import com.softhink.single.data.remote.response.UserResponse
import com.softhink.single.ui.base.BaseViewModel
import com.softhink.single.ui.registro.Status
import kotlinx.coroutines.launch

class MapViewModel: BaseViewModel() {

    private var usersLiveData = MutableLiveData<GenericObserver<List<UserResponse>>>()

    fun getListOfUsers() {
        scope.launch {
            val data = repository.makeRequest(UserTest("test", "", ""))
            if (data?.data.isNullOrEmpty()) {
                data?.status = Status.ERROR
            }
            usersLiveData.postValue(data)
        }
    }

    fun getListResponse() : MutableLiveData<GenericObserver<List<UserResponse>>> {
        return usersLiveData
    }
}