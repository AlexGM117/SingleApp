package com.softhink.single.base

import com.softhink.single.R
import com.softhink.single.models.response.BaseResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
abstract class BaseCallback<T> : Callback<BaseResponse<T>> {

    override fun onResponse(call: Call<BaseResponse<T>>, response: Response<BaseResponse<T>>) {
        if (response.body()?.responseCode?.equals("200")!!){
            if (response.body() != null)
                handleResponseData(response.body()!!.result!!, response.body()!!.responseMessage)
            else
                handleError(SingleApplication.applicationContext().getString(R.string.error_generic_message))
        } else {
            handleError(if (response.body()!!.responseMessage!!.isNullOrEmpty()) SingleApplication.applicationContext().getString(R.string.error_generic_message)
            else response.body()!!.responseMessage!!)
        }
    }

    override fun onFailure(call: Call<BaseResponse<T>>, t: Throwable) {
        if (t is Exception) {
            handleException(t)
        } else{
            //undefined
        }
    }

    protected abstract fun handleResponseData(data: T, message: String?)

    protected abstract fun handleError(message: String)

    protected abstract fun handleException(t: Exception)
}