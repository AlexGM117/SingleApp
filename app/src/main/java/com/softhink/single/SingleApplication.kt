package com.softhink.single

import android.app.Application
import android.content.Context
import com.crashlytics.android.core.CrashlyticsCore
import com.facebook.appevents.AppEventsLogger
import com.facebook.stetho.Stetho
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
        application = this
        AppEventsLogger.activateApp(this)
        Fabric.with(this, CrashlyticsCore.Builder().disabled(BuildConfig.FLAVOR == "dev").build())
        Stetho.initializeWithDefaults(this)
    }
}