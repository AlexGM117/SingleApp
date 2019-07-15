package com.softhink.single.data.manager

import com.softhink.single.ui.registro.Status
import androidx.lifecycle.Observer

class GenericObserver<T>(var status: Status, val data: T?, var message: String?) : Observer<T> {

    override fun onChanged(t: T) {}
}
