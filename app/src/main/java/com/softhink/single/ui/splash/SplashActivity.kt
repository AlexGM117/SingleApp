package com.softhink.single.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.softhink.single.R
import com.softhink.single.data.manager.SinglePreferences
import com.softhink.single.ui.base.BaseActivity
import com.softhink.single.ui.dashboard.MainContainer
import com.softhink.single.ui.login.view.LoginActivity

class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.SplashTheme)
        super.onCreate(savedInstanceState)
        if (checkPermissionsGranted(this)) {
            Handler().postDelayed({
                initSession()
            }, 3000)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSIONS_REQUEST_CODE) {
           initSession()
        }
    }

    private fun initSession() {
        var intent: Intent?
        if (!SinglePreferences().accessToken.isNullOrEmpty()) {
            intent = Intent(this, MainContainer::class.java)
        } else {
            intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
        }
        startActivity(intent)
        finish()
    }
}