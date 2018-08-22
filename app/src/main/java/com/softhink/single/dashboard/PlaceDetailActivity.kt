package com.softhink.single.dashboard

import android.os.Bundle
import com.softhink.single.BaseActivity
import com.softhink.single.R

class PlaceDetailActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_place_detail)

        setUpToolbar("Detalles", true)
    }
}