package com.softhink.single.login.ui

import android.os.Bundle
import com.softhink.single.BaseActivity
import com.softhink.single.Constants
import com.softhink.single.R

class LoginActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mFragmentManager.beginTransaction()
                .add(R.id.containerLogin, LoginFragment(),
                        Constants.LOGINFRAGMENT)
                .commit()
    }

}
