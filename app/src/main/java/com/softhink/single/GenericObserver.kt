package com.softhink.single

import com.softhink.single.registro.Status
import androidx.lifecycle.Observer

class GenericObserver<T>(val status: Status, val data: T) : Observer<T> {

    override fun onChanged(t: T) {}
}
