package com.softhink.single.base

import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel(){

    fun getString(string: Int): String {
        return SingleApplication.applicationContext().getString(string)
    }
}