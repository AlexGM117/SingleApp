package com.softhink.single.dashboard.places

import android.os.Bundle
import com.softhink.single.base.BaseActivity
import com.softhink.single.R

class PlaceDetailActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_place_detail)

        if(savedInstanceState == null) {
            setUpToolbar("Detalles", true)
        }
    }
}