package com.softhink.single.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.softhink.single.R
import com.softhink.single.data.manager.SinglePreferences
import com.softhink.single.ui.dashboard.MainContainer
import com.softhink.single.ui.login.view.LoginActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.SplashTheme)
        super.onCreate(savedInstanceState)

        Handler().postDelayed({
            val token = SinglePreferences().getAccessToken()
            var intent: Intent?
            if (token == null) {
                intent = Intent(this, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
            } else if (!SinglePreferences().getAccessToken().isNullOrEmpty()) {
                intent = Intent(this, MainContainer::class.java)
            } else {
                intent = Intent(this, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
            }
            startActivity(intent)
            finish()
        }, 3000)
    }
}