package com.softhink.single

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InteractorCallback(var callback: BaseView.Interactor<Any>) : Callback<Any> {

    override fun onFailure(call: Call<Any>, t: Throwable) {
        println(t.message)
        callback.onFailed()
    }

    override fun onResponse(call: Call<Any>, response: Response<Any>) {
        println(response.isSuccessful)
        if (response.code() == 200) {
            callback.onResponseSuccess(response.body())
        } else {
            callback.onResponseError(response.errorBody())
        }
    }
}