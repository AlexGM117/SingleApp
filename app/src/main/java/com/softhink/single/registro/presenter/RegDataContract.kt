package com.softhink.single.registro.presenter

import com.softhink.single.BaseView

interface RegDataContract : BaseView {
    fun nameIsEmpty()
    fun nameStringLenght()
    fun dateEmpty()
    fun isUnderAge()
    fun genderUnselected()
    fun toNextFragment()
}