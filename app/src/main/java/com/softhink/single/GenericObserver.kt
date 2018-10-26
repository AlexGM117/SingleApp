package com.softhink.single

import com.softhink.single.ui.registro.Status
import androidx.lifecycle.Observer

class GenericObserver<T>(val status: Status, val data: T?, val message: String?) : Observer<T> {

    override fun onChanged(t: T) {}
}
