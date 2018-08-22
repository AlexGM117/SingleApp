package com.softhink.single

import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.softhink.single.models.request.LoginRequest
import com.softhink.single.models.response.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

abstract class BaseActivity : AppCompatActivity() {

    lateinit var toolbar: Toolbar
    protected lateinit var mFragmentManager: FragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mFragmentManager = supportFragmentManager

        var request = LoginRequest()
        request.username = "test@mail.com"
        request.password = "password"
        var client = SingleClient.getInstance()

        val login : Call<LoginResponse>
        login = client.login(request)

        login.enqueue(object : Callback<LoginResponse> {
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                println(t.message)
            }

            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                println("${response.isSuccessful} ${response.code()} ${response.body().toString()}")
            }
        })
    }

    fun setUpToolbar(title: String, back: Boolean) {
        toolbar = findViewById(R.id.toolbar)
        toolbar.title = title
        toolbar.setTitleTextColor(getColor(R.color.colorGrayDark))
        toolbar.setTitleMargin(16, 0, 0, 0)

        if (back) {
            toolbar.setNavigationIcon(R.drawable.back)
        }
        setSupportActionBar(toolbar)
    }

    fun setNavigationUp() {
        toolbar.setNavigationIcon(R.drawable.back)
        setSupportActionBar(toolbar)
    }

    override fun onSupportNavigateUp(): Boolean {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        } else {
            finish()
        }
        return true
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        } else {
            finish()
        }
    }
}
