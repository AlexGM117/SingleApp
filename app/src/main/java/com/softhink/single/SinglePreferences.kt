package com.softhink.single

import android.content.Context
import com.softhink.single.base.SingleApplication

class SinglePreferences() {
    companion object {
        private val TAG = this::class.java.simpleName

        private val sharedPreferences = SingleApplication.applicationContext()
                .getSharedPreferences("com.softhink.single.prefs", Context.MODE_PRIVATE)

        private val KEY_ACCESS_TOKEN = "$TAG.KEY_ACCESS_TOKEN"
        private val KEY_FIRST_ACCESS = "$TAG.KEY_FIRST_ACCESS"
        private val KEY_SHOW_TUTORIAL = "$TAG.KEY_SHOW_TUTORIAL"
    }

    private var accessToken: String? = sharedPreferences.getString(KEY_ACCESS_TOKEN, null)

    var firstAccess: Boolean
        get() = sharedPreferences.getBoolean(KEY_FIRST_ACCESS, true)
        set(firstAccess) = sharedPreferences.edit().putBoolean(KEY_FIRST_ACCESS, firstAccess).apply()

    var showTutorial: Boolean
        get() = sharedPreferences.getBoolean(KEY_SHOW_TUTORIAL, true)
        set(showTutorial) = sharedPreferences.edit().putBoolean(KEY_SHOW_TUTORIAL, showTutorial).apply()

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