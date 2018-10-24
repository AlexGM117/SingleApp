package com.softhink.single

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.facebook.AccessToken
import com.softhink.single.ui.dashboard.MainContainer
import com.softhink.single.ui.login.ui.LoginActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.SplashTheme)
        super.onCreate(savedInstanceState)

        Handler().postDelayed({
            val accessToken = AccessToken.getCurrentAccessToken()
            var intent: Intent? = null
            if (accessToken == null) {
                intent = Intent(this, LoginActivity::class.java)
            } else if (!accessToken.isExpired){
                intent = Intent(this, MainContainer::class.java)
            }
            startActivity(intent)
            finish()
        }, 3000)
    }
}