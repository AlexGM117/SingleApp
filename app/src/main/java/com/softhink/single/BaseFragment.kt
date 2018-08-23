package com.softhink.single

import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.View
import com.afollestad.materialdialogs.MaterialDialog
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

    fun showImagePickerDialog(callback: onOptionsSelected) {
        val options = listOf("Seleccionar foto de la galeria", "Tomar foto de la camara")
        MaterialDialog(context!!)
                .title(text = "Selecciona")
                .listItems(items = options) { dialog, index, text ->
                    when(index){
                        0 -> callback.fromGalery()
                        1 -> callback.fromCamera()
                    }
                }
                .show()
    }

//    fun checkCameraPermissions(){
//        if (ContextCompat.checkSelfPermission(context!!, android.Manifest.permission.CAMERA)
//                != PackageManager.PERMISSION_GRANTED ){
//            ActivityCompat.requestPermissions(activity!!, arrayOf(android.Manifest.permission.CAMERA), 0)
//        }
//    }

    interface onOptionsSelected {
        fun fromGalery()
        fun fromCamera()
    }
}