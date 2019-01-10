package com.softhink.single.ui.survey

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.softhink.single.data.manager.GenericObserver
import com.softhink.single.data.manager.SingleRepository
import com.softhink.single.ui.base.BaseCallback
import com.softhink.single.ui.registro.Status

class SurveyViewModel : ViewModel() {

    private var repository = SingleRepository()
    var listGustos = MutableLiveData<GenericObserver<ArrayList<String>>>()
    var listHab = MutableLiveData<GenericObserver<ArrayList<String>>>()

    fun getLists() {
        repository.callListGustos(object : BaseCallback<List<String>>(){
            override fun handleResponseData(data: List<String>, message: String?) {
                if (data.isNotEmpty())
                    listGustos.value = GenericObserver(Status.SUCCESS, ArrayList(data), null)
            }

            override fun handleError(message: String, resultCode: String?) {
                listGustos.value = GenericObserver(Status.ERROR, null, null)
            }

            override fun handleException(t: Exception) {
                listGustos.value = GenericObserver(Status.FAILED, null, null)
            }
        })

        repository.callListHab(object : BaseCallback<List<String>>(){
            override fun handleResponseData(data: List<String>, message: String?) {
                if (data.isNotEmpty())
                    listHab.value = GenericObserver(Status.ERROR,
                            ArrayList(data), null)
            }

            override fun handleError(message: String, resultCode: String?) {
                listHab.value = GenericObserver(Status.ERROR, null, null)
            }

            override fun handleException(t: Exception) {
                listHab.value = GenericObserver(Status.ERROR, null, null)
            }
        })
    }
}