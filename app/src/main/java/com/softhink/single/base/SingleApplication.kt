package com.softhink.single.base

import android.app.Application
import android.content.Context
import com.crashlytics.android.core.CrashlyticsCore
import com.facebook.appevents.AppEventsLogger
import com.softhink.single.BuildConfig
import io.fabric.sdk.android.Fabric

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
        Fabric.with(this, CrashlyticsCore.Builder().disabled(BuildConfig.BUILD_TYPE == "debug").build())
    }
}