package com.softhink.single

abstract class BasePresenter : BaseView.Interactor<Any> {

    protected lateinit var interactor : Interactor

    abstract override fun onResponseSuccess(t: Any)

    abstract override fun onResponseError(t: Any)

    abstract override fun onFailed()
}