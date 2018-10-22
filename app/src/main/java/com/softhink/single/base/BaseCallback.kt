package com.softhink.single.base

import com.softhink.single.models.response.BaseResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
abstract class BaseCallback<T> : Callback<BaseResponse<T>> {

    override fun onResponse(call: Call<BaseResponse<T>>, response: Response<BaseResponse<T>>) {
        if(response.body() != null){
            if (response.body()?.responseCode == "200"){
                handleResponseData(response.body()?.result!!)
            } else {
                handleError(response.body()?.result)
            }
        } else{
            handleError(response.body()!!)
        }
    }

    override fun onFailure(call: Call<BaseResponse<T>>, t: Throwable) {
        if (t is Exception) {
            handleException(t)
        } else{

        }
    }

    protected abstract fun handleResponseData(data: T)

    protected abstract fun handleError(error: T?)

    protected abstract fun handleException(t: Exception)
}