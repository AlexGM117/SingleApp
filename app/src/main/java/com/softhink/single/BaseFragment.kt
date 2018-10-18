package com.softhink.single

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.core.content.ContextCompat
import android.view.View
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView
import com.afollestad.materialdialogs.list.listItems

abstract class BaseFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    fun showMessageDialog(message: String){
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
    fun showMessageDialogGalery(listener: DialogCallBack){
        MaterialDialog(context!!)
                .customView(R.layout.dialog_galery_access)
                .positiveButton(text = "ACEPTAR"){
                    listener.onAccept()
                }
                .negativeButton(text = "CANCELAR"){
                    listener.onCancel()
                }
                .show()
    }

    fun showMessageDialog(message: String, listener: DialogCallBack){
        MaterialDialog(context!!)
                .title(R.string.app_name)
                .message(text = message)
                .positiveButton(text = "Continuar"){
                    listener.onAccept()
                }
                .show()
    }

    interface OnOptionsSelected {
        fun fromGalery()
        fun fromCamera()
    }
}