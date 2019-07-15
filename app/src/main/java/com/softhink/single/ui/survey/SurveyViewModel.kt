package com.softhink.single.ui.survey

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.softhink.single.data.manager.GenericObserver
import com.softhink.single.data.remote.request.SaveSurveyRequest
import com.softhink.single.data.remote.response.BaseObject
import com.softhink.single.data.remote.response.EncuestaResponse
import com.softhink.single.data.remote.response.SurveyResponse
import com.softhink.single.ui.base.BaseViewModel
import com.softhink.single.ui.registro.Status
import kotlinx.coroutines.launch

class SurveyViewModel : BaseViewModel() {

    private var servicesResponse = MutableLiveData<GenericObserver<List<SurveyResponse>>>()
    var listGustos = MutableLiveData<List<BaseObject>>()
    var listHab = MutableLiveData<List<BaseObject>>()
    private var listGender = ArrayList<BaseObject>()
    private var request = SaveSurveyRequest()
    var surveyResponse = MutableLiveData<GenericObserver<EncuestaResponse>>()

    fun savePreferences(man: Boolean, woman: Boolean, visible: Boolean, minAge: Int, maxAge: Int) {
        request.username = "Test09022019123003093"
        request.preferencia = filterList(man, woman)
        request.visible = if (visible) "1" else "0"
        request.minima = minAge.toString()
        request.maxima = maxAge.toString()
    }

    fun saveInterests(listSelected: ArrayList<String>) {
        request.gustos = filterList(listSelected)
    }

    fun saveTastes(listSelected: ArrayList<String>) {
        request.habitos = filterList2(listSelected)
        sendSurvey()
    }

    private fun filterList(listSelected: ArrayList<String>): List<BaseObject>? {
        val list = ArrayList<BaseObject>()
        for (x in listSelected) {
            for (item in listHab.value!!) {
                if (x == item.nombre) {
                    list.add(item)
                }
            }
        }
        return list
    }

    private fun filterList2(listSelected: ArrayList<String>): List<BaseObject>? {
        val list = ArrayList<BaseObject>()
        for (x in listSelected) {
            for (item in listGustos.value!!) {
                if (x == item.nombre) {
                    list.add(item)
                }
            }
        }
        return list
    }

    private fun filterList(man: Boolean, woman: Boolean): List<BaseObject>? {
        val list = ArrayList<BaseObject>()
        if (man) {
            for (item in listGender) {
                if (item.nombre == "Hombre")
                    list.add(item)
            }
        }
        if(woman) {
            for (item in listGender) {
                if (item.nombre == "Mujer")
                    list.add(item)
            }
        }
        return list
    }

    fun getLists() : LiveData<GenericObserver<List<SurveyResponse>>> {
        scope.launch {
            val data = repository.makeRequest()
            if (data?.data.isNullOrEmpty() || data?.data?.get(0)?.tastesList.isNullOrEmpty()
                    || data?.data?.get(0)?.genders.isNullOrEmpty() || data?.data?.get(0)?.interestList.isNullOrEmpty()) {
                data?.status = Status.ERROR
            }
            servicesResponse.postValue(data)
        }
        return servicesResponse
    }

    private fun sendSurvey() {
        scope.launch {
            surveyResponse.postValue(repository.makeRequest(request))
        }
    }

    fun setUsername(username: String) {
        request.username = username
    }
}