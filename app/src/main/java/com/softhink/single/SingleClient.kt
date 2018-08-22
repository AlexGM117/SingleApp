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

    fun getInstance() : SingleService {
        clientBuilder = OkHttpClient.Builder()
        clientBuilder.readTimeout(30, TimeUnit.SECONDS)
        clientBuilder.writeTimeout(30, TimeUnit.SECONDS)

        if (BuildConfig.DEBUG){
            loggingInterceptor = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
                override fun log(message: String) {
                    Log.d("Single Services:", message)
                }
            })
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            clientBuilder.interceptors().add(loggingInterceptor)
        }


        clientBuilder.addInterceptor(buildInterceptor())

        return Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(clientBuilder.build())
                .build()
                .create(SingleService::class.java)
    }

    private fun buildInterceptor() : Interceptor {
        return Interceptor { chain ->
            var request: Request = chain.request()
            val headers = request.headers().newBuilder()
                    .add("JsonStub-User-Key", "bca9357c-bbbf-4fbd-a03c-ce38d8b3e1b1")
                    .add("JsonStub-Project-Key", "66dd5c7a-8ad6-4422-bbd6-77fbaa6435d2")
                    .build()
            request = request.newBuilder().headers(headers).build()
            chain.proceed(request)
        }
    }
}