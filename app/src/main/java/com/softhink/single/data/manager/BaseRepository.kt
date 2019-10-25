package com.softhink.single.data.manager

import com.softhink.single.data.remote.response.BaseResponse
import com.softhink.single.ui.registro.Status
import retrofit2.Response
import java.io.IOException
import java.lang.Exception

open class BaseRepository {
    suspend fun <T> safeApiCall(call: suspend() -> Response<BaseResponse<T>>, errorMessage: String): GenericObserver<T>? {
        val result = safeApiResult(call, errorMessage)
        var data: GenericObserver<T>? = null
        when (result) {
            is Result.Success -> {
                if (result.data.responseCode == "200") {
                    if (result.data.responseData != null) {
                        if (result.data.responseData is List<*>) {
                            if (!result.data.responseData.isNullOrEmpty()) {
                                data = GenericObserver(Status.SUCCESS, result.data.responseData, result.data.responseMessage)
                            } else {
                                data = GenericObserver(Status.ERROR, null, if(result.data.responseMessage != null) result.data.responseMessage else errorMessage)
                            }
                        } else {
                            data = GenericObserver(Status.SUCCESS, result.data.responseData, result.data.responseMessage)
                        }
                    } else {
                        data = GenericObserver(Status.ERROR, null, if(result.data.responseMessage != null) result.data.responseMessage else errorMessage)
                    }
                } else {
                    data = GenericObserver(Status.ERROR, result.data.responseData, if(result.data.responseMessage != null) result.data.responseMessage else errorMessage)
                }
            }

            is Result.Error -> data = GenericObserver(Status.ERROR, null, errorMessage)
        }

        return data
    }

    private suspend fun <T> safeApiResult(call: suspend() -> Response<BaseResponse<T>>, errorMessage: String) : Result<BaseResponse<T>> {
        try {
            val response = call.invoke()
            if (response.isSuccessful && response.body() != null) {
                return Result.Success(response.body()!!)
            }
        } catch (e: Exception) {
            return Result.Error(e)
        }

        return Result.Error(IOException("Error Occurred during getting safe Api result, Custom ERROR - $errorMessage"))
    }
}