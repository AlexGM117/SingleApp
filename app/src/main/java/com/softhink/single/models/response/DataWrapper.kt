package com.softhink.single.models.response

import java.lang.Exception

class DataWrapper<T> {
    var exception: Exception? = null
    var data : T? = null
}