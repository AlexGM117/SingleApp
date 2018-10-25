package com.softhink.single

interface DialogCallBack {

    interface SingleCallback{
        fun onAccept()
    }

    interface Callback{
        fun onAccept()
        fun onCancel()
    }
}