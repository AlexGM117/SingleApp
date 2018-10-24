package com.softhink.single

import android.content.Context
import com.softhink.single.base.SingleApplication

class SinglePreferences() {
    companion object {
        private val sharedPreferences = SingleApplication.applicationContext()
                .getSharedPreferences("com.softhink.single.prefs", Context.MODE_PRIVATE)
        private val TAG = this::class.java.simpleName
        private val KEY_ACCESS_TOKEN = "$TAG.KEY_ACCESS_TOKEN"
    }

    private var accessToken: String? = sharedPreferences.getString(KEY_ACCESS_TOKEN, null)

    fun setAccessToken(accessToken: String?) {
        setPreferences(KEY_ACCESS_TOKEN, accessToken)
    }

    fun getAccessToken() = accessToken

    private fun setPreferences(key: String, value: String?){
        val editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.apply()
    }
}