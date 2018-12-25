package com.softhink.single.base

import android.Manifest
import android.content.pm.PackageManager
import androidx.fragment.app.Fragment
import androidx.core.content.ContextCompat
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView
import com.afollestad.materialdialogs.list.listItems
import com.softhink.single.DialogCallBack
import com.softhink.single.NetworkUtil
import com.softhink.single.R

abstract class BaseFragment : Fragment() {

    fun updateToolbar(title: String, back: Boolean){
        val toolbar = activity!! as BaseActivity
        toolbar.setUpToolbar(title, back)
    }

    fun qshowMessageDialog(message: String){
        MaterialDialog(context!!)
                .title(R.string.app_name)
                .message(text = message)
                .positiveButton(text = "Continuar")
                .show()
    }

    fun showImagePickerDialog(callback: OnOptionsSelected) {
        val options = listOf("Seleccionar foto de la galeria", "Tomar foto de la camara")
        MaterialDialog(context!!)
                .title(text = "Selecciona")
                .listItems(items = options) { _, index, _ ->
                    when(index){
                        0 -> callback.fromGalery()
                        1 -> callback.fromCamera()
                    }
                }
                .show()
    }

    fun checkPermissions(): Boolean{
        if(ContextCompat.checkSelfPermission(context!!, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){
            return false
        }

        return true
    }
    fun showMessageDialogGalery(listener: DialogCallBack.SingleCallback){
        MaterialDialog(context!!)
                .customView(R.layout.dialog_galery_access)
                .positiveButton(text = "ACEPTAR"){
                    listener.onAccept()
                }
                .negativeButton(text = "CANCELAR")
                .show()
    }

    fun showMessageDialog(message: String, listener: DialogCallBack.SingleCallback){
        MaterialDialog(context!!)
                .title(R.string.app_name)
                .message(text = message)
                .positiveButton(text = "Continuar"){
                    listener.onAccept()
                }
                .show()
    }

    fun showMessageDialog(title: String, message: String, listener: DialogCallBack.Callback){
        MaterialDialog(context!!)
                .title(text = title)
                .message(text = message)
                .positiveButton(text = "CONTINUAR"){
                    listener.onAccept()
                }
                .negativeButton(text = "CANCELAR") {
                    listener.onCancel()
                }
                .show()
    }

    fun showMessageDialog(message: String){
        MaterialDialog(context!!)
                .title(text = getString(R.string.app_name))
                .message(text = message)
                .positiveButton(text = "ACEPTAR")
                .show()
    }

    fun isConnected() : Boolean{
        return NetworkUtil().isOnline(context!!)
    }

    interface OnOptionsSelected {
        fun fromGalery()
        fun fromCamera()
    }
}