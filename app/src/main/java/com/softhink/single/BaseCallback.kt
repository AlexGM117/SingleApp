package com.softhink.single

import android.util.Log

import com.softhink.single.models.response.BaseResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BaseCallback<T, U>(private val callback: BaseView.Interactor<T, U>) : Callback<BaseResponse<T, U>> {

    override fun onResponse(call: Call<BaseResponse<T, U>>, response: Response<BaseResponse<T, U>>) {
        if (response.code() == 200) {
            if (response.body() != null) {
                if (response.body()?.getResponseCode() == 0) {
                    callback.onResponseSuccess(response.body())
                } else if (response.body()?.getResponseCode() == -1) {
                    callback.onResponseError(response.body()!!.error)
                }
            } else {
                callback.onFailed()
            }
        } else {
            callback.onResponseError(response.body()?.error)
        }
    }

    override fun onFailure(call: Call<BaseResponse<T, U>>, t: Throwable) {
        Log.d("Single Services: ", t.message)
        callback.onFailed()
    }
}