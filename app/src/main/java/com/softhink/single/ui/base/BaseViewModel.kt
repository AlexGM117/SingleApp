package com.softhink.single.ui.base

import androidx.lifecycle.ViewModel
import com.softhink.single.SingleApplication

abstract class BaseViewModel : ViewModel(){

    fun getString(string: Int): String {
        return SingleApplication.applicationContext().getString(string)
    }
}