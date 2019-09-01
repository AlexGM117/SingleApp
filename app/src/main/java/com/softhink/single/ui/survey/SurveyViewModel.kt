package com.softhink.single.ui.survey

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.softhink.single.data.manager.GenericObserver
import com.softhink.single.data.remote.request.SurveyRequest
import com.softhink.single.data.remote.response.BaseObject
import com.softhink.single.data.remote.response.EncuestaResponse
import com.softhink.single.data.remote.response.SurveyResponse
import com.softhink.single.ui.base.BaseViewModel
import com.softhink.single.ui.registro.Status
import kotlinx.coroutines.launch

class SurveyViewModel : BaseViewModel() {

    private var servicesResponse = MutableLiveData<GenericObserver<SurveyResponse>>()
    var request = SurveyRequest()
    var surveyResponse = MutableLiveData<GenericObserver<EncuestaResponse>>()

    fun savePreferences(man: Boolean, woman: Boolean, visible: Boolean, minAge: Int, maxAge: Int) {
        request.preferencia = filterList(man, woman)
        request.visible = if (visible) "1" else "0"
        request.minima = minAge.toString()
        request.maxima = maxAge.toString()
    }

    fun saveInterests(listSelected: ArrayList<String>) {
        request.intereses = filterList(listSelected)
    }

    fun saveTastes(listSelected: ArrayList<String>) {
        request.consumo = filterList(listSelected)
        sendSurvey()
    }

    private fun filterList(listSelected: ArrayList<String>): List<BaseObject>? {
        val list = ArrayList<BaseObject>()
        val listas = ArrayList(servicesResponse.value?.data!!.interestList)
        listas.addAll(servicesResponse.value?.data!!.tastesList)
        for (x in listSelected) {
            for (item in listas) {
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
            for (item in servicesResponse.value?.data!!.genders) {
                if (item.nombre == "Hombre")
                    list.add(item)
            }
        }
        if(woman) {
            for (item in servicesResponse.value?.data!!.genders) {
                if (item.nombre == "Mujer")
                    list.add(item)
            }
        }
        return list
    }

    fun getLists() : LiveData<GenericObserver<SurveyResponse>> {
        scope.launch {
            val data = repository.makeRequest()
            if (data!!.data == null || data.data?.interestList.isNullOrEmpty() || data.data?.genders.isNullOrEmpty() ||
                    data.data?.tastesList.isNullOrEmpty()) {
                data.status = Status.ERROR
            }
            servicesResponse.postValue(data)
        }
        return servicesResponse
    }

    fun getElementsOfList(string: String) : List<BaseObject>? {
        return when (string) {
            "interes" -> servicesResponse.value?.data?.interestList
            "consumo" -> servicesResponse.value?.data?.tastesList
            else -> return null
        }
    }

    private fun sendSurvey() {
        scope.launch {
            surveyResponse.postValue(repository.makeRequest(request))
        }
    }
}