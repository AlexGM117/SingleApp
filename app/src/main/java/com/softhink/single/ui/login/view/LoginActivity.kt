package com.softhink.single.ui.login.view

import android.os.Bundle
import com.softhink.single.ui.base.BaseActivity
import com.softhink.single.util.Constants
import com.softhink.single.R

class LoginActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mFragmentManager.beginTransaction()
                .add(R.id.containerLogin, LoginFragment(),
                        Constants.LOGINFRAGMENT)
                .commitNow()
    }

}