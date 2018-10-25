package com.softhink.single.base

import android.app.Application
import android.content.Context
import com.facebook.FacebookSdk
import com.facebook.appevents.AppEventsLogger

class SingleApplication : Application() {
    init {
        application = this
    }

    companion object {
        private var application: Application? = null

        fun applicationContext(): Context{
            return application!!.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        AppEventsLogger.activateApp(this)
        application = this
    }
}