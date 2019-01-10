package com.softhink.single.ui.registro.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.softhink.single.ui.base.BaseActivity
import com.softhink.single.util.Constants
import com.softhink.single.R
import com.softhink.single.ui.login.view.LoginActivity

class SignUpActivity : BaseActivity(), View.OnClickListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        if (savedInstanceState == null) {
            setUpToolbar("Registro", this)
            mFragmentManager = supportFragmentManager
            mFragmentManager.beginTransaction()
                    .add(R.id.signUpContainer, SignUpDataFragment(),
                            Constants.REGISTROUNOFRAGMENT)
                    .commit()
        }
    }

    override fun onClick(view: View) {
        if (mFragmentManager.backStackEntryCount > 0) {
            mFragmentManager.popBackStack()
        } else {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    override fun onBackPressed() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
}