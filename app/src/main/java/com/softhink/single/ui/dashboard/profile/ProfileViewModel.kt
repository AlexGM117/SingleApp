package com.softhink.single.ui.dashboard.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.softhink.single.SingleData
import com.softhink.single.data.manager.GenericObserver
import com.softhink.single.data.manager.SinglePreferences
import com.softhink.single.data.remote.request.UserRequest
import com.softhink.single.data.remote.request.UserTest
import com.softhink.single.data.remote.response.UserProfile
import com.softhink.single.data.remote.response.UserResponse
import com.softhink.single.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class ProfileViewModel: BaseViewModel() {

    var mData: LiveData<List<SingleData>>? = null
    var liveDta = MutableLiveData<GenericObserver<UserProfile>>()

    init {
        getMyProfile()
        getDataFromLocal()
    }

    fun getMyProfile() = scope.launch {
            liveDta.postValue(repository.makeRequest(UserTest(SinglePreferences().accessToken!!)))
    }

    fun updateProfile(newName: String, toString1: String, newAboutMe: String) : LiveData<GenericObserver<UserResponse>> {
        val result = MutableLiveData<GenericObserver<UserResponse>>()
        val request = UserRequest()
        request.userId = SinglePreferences().accessToken
        request.fullName = newName
        request.description = newAboutMe
        if (toString1.isNotEmpty()) request.password = toString1
        scope.launch {
            result.postValue(repository.makeRequest(request))
        }
        return result
    }

    private fun getDataFromLocal() {
        mData = roomRepository.
                getProfileFromRoom()
    }

    fun updateLocaStore(data: UserResponse, description: String) {
        scope.launch {
            roomRepository.insert(SingleData(data.username, data.fullName, description, data.birthDate, data.email, null, null, data.fechaAlta, null))
        }
    }
}