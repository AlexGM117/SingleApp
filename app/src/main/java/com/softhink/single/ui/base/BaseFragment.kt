package com.softhink.single.ui.base

import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.core.content.ContextCompat
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView
import com.afollestad.materialdialogs.list.listItems
import com.softhink.single.util.NetworkUtil
import com.softhink.single.R

abstract class BaseFragment : Fragment() {

    protected val PERMISSION_REQUEST_CODE = 123

    fun updateToolbar(title: String, back: Boolean){
        val toolbar = activity!! as BaseActivity
        toolbar.setUpToolbar(title, back)
    }

    inline fun showPermissionsDialog(crossinline positiveClick:() -> Unit){
        MaterialDialog(context!!)
                .customView(R.layout.dialog_galery_access)
                .positiveButton(text = "ACEPTAR"){
                    positiveClick()
                }
                .negativeButton(text = "CANCELAR")
                .show()
    }

    inline fun showMessageDialog(message: String, crossinline positiveClick:() -> Unit = {}) {
        MaterialDialog(context!!)
                .title(R.string.app_name)
                .message(text = message)
                .positiveButton(text = "Continuar"){
                    positiveClick()
                }
                .show()
    }

    inline fun showMessageDialog(title: String, message: String, crossinline positiveClick:() -> Unit = {}, crossinline  negativeClick:() -> Unit = {}){
        MaterialDialog(context!!)
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

    inline fun showMessageDialog(crossinline fromGalery:() -> Unit, crossinline  fromCamera:() -> Unit) {
        val options = listOf("Seleccionar foto de la galeria", "Tomar foto de la camara")
        MaterialDialog(context!!)
                .title(text = "Selecciona")
                .listItems(items = options) { _, index, _ ->
                    when(index){
                        0 -> fromGalery()
                        1 -> fromCamera()
                    }
                }
                .show()
    }

    fun isConnected() : Boolean{
        return NetworkUtil().isOnline(context!!)
    }

    fun checkPermissionGranted(perm: Array<String>) : Boolean {
        val listPermissionsNeeded = ArrayList<String>()

        for (permission in perm) {
            if (ContextCompat.checkSelfPermission(activity!!, permission) != PackageManager.PERMISSION_GRANTED)
                listPermissionsNeeded.add(permission)
        }

        if (listPermissionsNeeded.isNotEmpty()) {
            showPermissionsDialog(positiveClick = {
                ActivityCompat.requestPermissions(activity!!, perm, PERMISSION_REQUEST_CODE)
            })
            return false
        }

        return true
    }
}