package com.softhink.single.ui.base

import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.View
import androidx.core.content.ContextCompat
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView
import com.softhink.single.util.NetworkUtil
import com.softhink.single.R

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
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.colorGrayDark))
        toolbar.setTitleMargin(16, 0, 0, 0)
        setSupportActionBar(toolbar)

        if (back) {
            toolbar.setNavigationIcon(R.drawable.ic_back)
            toolbar.setNavigationOnClickListener {
                if (supportFragmentManager.backStackEntryCount > 0) {
                    supportFragmentManager.popBackStack()
                } else {
                    finish()
                }
            }
        }
    }

    fun setUpToolbar(title: String, listener: View.OnClickListener){
        toolbar = findViewById(R.id.toolbar)
        toolbar.title = title
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.colorGrayDark))
        toolbar.setTitleMargin(16, 0, 0, 0)
        setSupportActionBar(toolbar)
        toolbar.setNavigationIcon(R.drawable.ic_back)
        toolbar.setNavigationOnClickListener(listener)
    }

    inline fun showMessageDialog(crossinline positiveClick:() -> Unit){
        MaterialDialog(this)
                .customView(R.layout.dialog_galery_access)
                .positiveButton(text = "ACEPTAR"){
                    positiveClick()
                }
                .negativeButton(text = "CANCELAR")
                .show()
    }

    inline fun showMessageDialog(message: String, crossinline positiveClick:() -> Unit = {}) {
        MaterialDialog(this)
                .title(R.string.app_name)
                .message(text = message)
                .positiveButton(text = "Continuar"){
                    positiveClick()
                }
                .show()
    }

    inline fun showMessageDialog(title: String, message: String, crossinline positiveClick:() -> Unit = {}, crossinline  negativeClick:() -> Unit = {}){
        MaterialDialog(this)
                .title(text = title)
                .message(text = message)
                .positiveButton(text = "CONTINUAR"){
                    positiveClick()
                }
                .negativeButton(text = "CANCELAR") {
                    negativeClick()
                }
                .show()
    }

    fun isConnected() : Boolean{
        return NetworkUtil().isOnline(this)
    }
}
