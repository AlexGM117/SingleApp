package com.softhink.single.ui.base

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView
import com.softhink.single.R
import com.softhink.single.util.NetworkUtil

abstract class BaseActivity : AppCompatActivity() {

    lateinit var toolbar: Toolbar
    protected lateinit var mFragmentManager: FragmentManager
    protected var PERMISSIONS_REQUEST_CODE = 123

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

    fun showMessageDialog(message: String?) {
        MaterialDialog(this)
                .title(R.string.app_name)
                .message(text = message)
                .positiveButton(text = "ACEPTAR"){
                    finish()
                }
                .show()
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

    fun checkPermissionsGranted(activity: Activity) : Boolean {
        val permissions = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA)

        val listPermissionsNeeded = ArrayList<String>()

        for (permission in permissions) {
            if (ContextCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED)
                listPermissionsNeeded.add(permission)
        }

        if (listPermissionsNeeded.isNotEmpty()) {
            ActivityCompat.requestPermissions(activity, permissions, PERMISSIONS_REQUEST_CODE)
            return false
        }

        return true
    }

    fun checkPermissionGranted(perm: Array<String>) : Boolean {
        val listPermissionsNeeded = ArrayList<String>()

        for (permission in perm) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED)
                listPermissionsNeeded.add(permission)
        }

        if (listPermissionsNeeded.isNotEmpty()) {
            ActivityCompat.requestPermissions(this, perm, PERMISSIONS_REQUEST_CODE)
            return false
        }

        return true
    }
}
