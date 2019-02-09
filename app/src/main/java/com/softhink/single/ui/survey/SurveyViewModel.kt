package com.softhink.single.ui.survey

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.softhink.single.data.manager.GenericObserver
import com.softhink.single.data.manager.SingleRepository
import com.softhink.single.data.remote.request.SaveSurveyRequest
import com.softhink.single.data.remote.response.BaseObject
import com.softhink.single.data.remote.response.EncuestaResponse
import com.softhink.single.data.remote.response.SurveyResponse
import com.softhink.single.ui.base.BaseCallback
import com.softhink.single.ui.registro.Status

class SurveyViewModel : ViewModel() {

    private var repository = SingleRepository()
    private var servicesResponse = MutableLiveData<GenericObserver<Any>>()
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

    fun getLists() :LiveData<GenericObserver<Any>> {
        repository.callListGustos(object : BaseCallback<List<SurveyResponse>>(){
            override fun handleResponseData(data: List<SurveyResponse>, message: String?) {
                if (!data.isNullOrEmpty() && !data[0].interestList.isNullOrEmpty() && !data[0].tastesList.isNullOrEmpty() && !data[0].genders.isNullOrEmpty()) {
                    servicesResponse.value = GenericObserver(Status.SUCCESS, null, null)
                    listGustos.value = data[0].tastesList
                    listHab.value = data[0].interestList
                    listGender = data[0].genders as ArrayList<BaseObject>
                } else {
                    servicesResponse.value = GenericObserver(Status.ERROR, null, null)
                }
            }

            override fun handleError(message: String, resultCode: String?) {
                servicesResponse.value = GenericObserver(Status.ERROR, null, null)
            }

            override fun handleException(t: Exception) {
                servicesResponse.value = GenericObserver(Status.FAILED, null, null)
            }
        })

        return servicesResponse
    }

    private fun sendSurvey() {
        repository.callSendSurvey(request, object : BaseCallback<EncuestaResponse>(){
            override fun handleResponseData(data: EncuestaResponse, message: String?) {
                surveyResponse.value = GenericObserver(Status.SUCCESS, data, message)
            }

            override fun handleError(message: String, resultCode: String?) {
                surveyResponse.value = GenericObserver(Status.ERROR, null, message)
            }

            override fun handleException(t: Exception) {
                surveyResponse.value = GenericObserver(Status.FAILED, null, t.message)
            }

        })
    }

    fun setUsername(username: String) {
        request.username = username
    }
}