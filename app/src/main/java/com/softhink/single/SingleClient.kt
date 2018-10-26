package com.softhink.single

import android.util.Log
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object SingleClient {
    lateinit var clientBuilder : OkHttpClient.Builder
    var loggingInterceptor = HttpLoggingInterceptor()
    private var BASE_URL = "http://singleservices.ddns.net/" //PRO

    fun getInstance() : SingleService {
        clientBuilder = OkHttpClient.Builder()
        clientBuilder.readTimeout(30, TimeUnit.SECONDS)
        clientBuilder.writeTimeout(30, TimeUnit.SECONDS)

        if (BuildConfig.DEBUG){
            //DEV
//            BASE_URL = "http://singleservices.ddns.net/registro/login"

            loggingInterceptor = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
                override fun log(message: String) {
                    Log.d("Single Services:", message)
                }
            })
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            clientBuilder.interceptors().add(loggingInterceptor)
        }

        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(clientBuilder.build())
                .build()
                .create(SingleService::class.java)
    }
}