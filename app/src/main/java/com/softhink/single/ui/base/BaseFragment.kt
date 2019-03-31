package com.softhink.single.ui.base

import android.Manifest
import android.content.pm.PackageManager
import androidx.fragment.app.Fragment
import androidx.core.content.ContextCompat
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView
import com.afollestad.materialdialogs.list.listItems
import com.softhink.single.util.NetworkUtil
import com.softhink.single.R

abstract class BaseFragment : Fragment() {

    fun updateToolbar(title: String, back: Boolean){
        val toolbar = activity!! as BaseActivity
        toolbar.setUpToolbar(title, back)
    }

    fun checkPermissions(): Boolean{
        if(ContextCompat.checkSelfPermission(context!!, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){
            return false
        }

        return true
    }

    inline fun showMessageDialog(crossinline positiveClick:() -> Unit){
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
}