package com.softhink.single.ui.base

import androidx.lifecycle.ViewModel
import com.softhink.single.SingleApplication
import com.softhink.single.data.manager.ApiRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel : ViewModel() {

    private val parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Default
    protected val scope = CoroutineScope(coroutineContext)
    protected val repository = ApiRepository()

    fun getString(string: Int): String {
        return SingleApplication.applicationContext().getString(string)
    }
}