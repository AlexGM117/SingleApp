package com.softhink.single.ui.dashboard

import androidx.lifecycle.MutableLiveData
import com.google.android.gms.maps.model.LatLng
import com.softhink.single.data.manager.GenericObserver
import com.softhink.single.data.manager.SinglePreferences
import com.softhink.single.data.remote.request.SinglearRequest
import com.softhink.single.data.remote.request.UserTest
import com.softhink.single.data.remote.response.UserResponse
import com.softhink.single.ui.base.BaseViewModel
import com.softhink.single.ui.registro.Status
import kotlinx.coroutines.launch

class MapViewModel: BaseViewModel() {

    private var usersLiveData = MutableLiveData<GenericObserver<List<UserResponse>>>()
    var liveCoordinates = MutableLiveData<LatLng>()

    fun getListOfUsers() {
        val lat: String = liveCoordinates.value?.latitude.toString()
        val lng: String = liveCoordinates.value?.latitude.toString()
        scope.launch {
            val data = repository.makeRequest(SinglearRequest(SinglePreferences().accessToken!!, lat, lng))
            usersLiveData.postValue(data)
        }
    }

    fun getListResponse() : MutableLiveData<GenericObserver<List<UserResponse>>> {
        return usersLiveData
    }

    fun updateLocation(latlng: LatLng){
        liveCoordinates.value = latlng
        getListOfUsers()
    }
}