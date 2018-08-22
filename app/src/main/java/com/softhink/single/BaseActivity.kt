package com.softhink.single

import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar

abstract class BaseActivity : AppCompatActivity() {

    lateinit var toolbar: Toolbar
    protected lateinit var mFragmentManager: FragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mFragmentManager = supportFragmentManager
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
