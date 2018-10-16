package com.softhink.single.login.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.softhink.single.BaseCallback
import com.softhink.single.models.response.BaseResponse
import com.softhink.single.models.response.DataWrapper
import retrofit2.Call

abstract class GenericRequestHandler<R>{

    protected abstract fun makeRequest() : Call<BaseResponse<R>>

    fun doRequest() : LiveData<DataWrapper<R>> {

        val liveData: MutableLiveData<DataWrapper<R>>? = null
        val dataWrapper: DataWrapper<R>? = null

        makeRequest().enqueue(object : BaseCallback<R>() {
            override fun handleResponseData(data: R) {
                dataWrapper?.data = data
                liveData?.value = dataWrapper
            }

            override fun handleError(response: BaseResponse<R>) {
                dataWrapper?.data = response.error
                liveData?.value = dataWrapper
            }

            override fun handleException(t: Exception) {
                dataWrapper?.exception = t
                liveData?.value = dataWrapper
            }
        })

        return liveData!!
    }
}