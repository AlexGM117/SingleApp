package com.softhink.single

import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import com.afollestad.materialdialogs.MaterialDialog
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
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        } else {
            finish()
        }
    }

    fun setUpToolbar(title: String, back: Boolean) {
        toolbar = findViewById(R.id.toolbar)
        toolbar.title = title
        toolbar.setTitleTextColor(resources.getColor(R.color.colorGrayDark))
        toolbar.setTitleMargin(16, 0, 0, 0)
        setSupportActionBar(toolbar)

        if (back) {
            toolbar.setNavigationIcon(R.drawable.back)
            toolbar.setNavigationOnClickListener {
                if (supportFragmentManager.backStackEntryCount > 0) {
                    supportFragmentManager.popBackStack()
                } else {
                    finish()
                }
            }
        }
    }

    fun setNavigationUp() {
        toolbar.setNavigationIcon(R.drawable.back)
        toolbar.setNavigationOnClickListener {
            if (supportFragmentManager.backStackEntryCount > 0) {
                supportFragmentManager.popBackStack()
            } else {
                finish()
            }
        }
    }

    fun setUpToolbar(title: String, listener: View.OnClickListener){
        toolbar = findViewById(R.id.toolbar)
        toolbar.title = title
        toolbar.setTitleTextColor(resources.getColor(R.color.colorGrayDark))
        toolbar.setTitleMargin(16, 0, 0, 0)
        setSupportActionBar(toolbar)
        toolbar.setNavigationIcon(R.drawable.back)
        toolbar.setNavigationOnClickListener(listener)
    }

    fun showMessageDialog(message: String){
        MaterialDialog(this)
                .title(R.string.app_name)
                .message(text = message)
                .positiveButton(text = "Continuar")
                .show()
    }
}
