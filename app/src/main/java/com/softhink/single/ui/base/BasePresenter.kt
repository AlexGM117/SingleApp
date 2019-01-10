package com.softhink.single.ui.base

import com.softhink.single.data.manager.SingleRepository

abstract class BasePresenter {

    protected lateinit var repository : SingleRepository
}