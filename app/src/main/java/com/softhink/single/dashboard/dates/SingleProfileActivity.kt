package com.softhink.single.dashboard.dates

import android.os.Bundle
import com.softhink.single.BaseActivity
import com.softhink.single.R

class DateDetailActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_date_detail)
        setUpToolbar("Detalles del perfil", true)
        
    }
}
