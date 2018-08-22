package com.softhink.single

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import com.afollestad.materialdialogs.MaterialDialog

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
}