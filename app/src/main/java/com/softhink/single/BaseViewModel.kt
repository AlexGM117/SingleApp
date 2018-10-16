package com.softhink.single

import com.softhink.single.login.ui.GenericRequestHandler
import com.softhink.single.models.response.BaseResponse
import retrofit2.Call

abstract class BaseViewModel<T> : GenericRequestHandler<T>() {

    abstract override fun makeRequest(): Call<BaseResponse<T>>
}