package com.softhink.single

import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.View
import com.afollestad.materialdialogs.MaterialDialog

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

    fun showMessageDialog(message: String, listener: DialogCallBack){
        MaterialDialog(this)
                .title(R.string.app_name)
                .message(text = message)
                .positiveButton(text = "Continuar"){
                    listener.onAccept()
                }
                .show()
    }
}
