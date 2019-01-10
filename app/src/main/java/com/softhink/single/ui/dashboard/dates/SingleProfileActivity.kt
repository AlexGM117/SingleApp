package com.softhink.single.ui.dashboard.dates

import android.os.Bundle
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.softhink.single.ui.base.BaseActivity
import com.softhink.single.R
import com.softhink.single.ui.dashboard.adapters.GaleryAdapter
import kotlinx.android.synthetic.main.activity_single_profile.*

class SingleProfileActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_profile)
        setUpToolbar("Detalles del perfil", true)

        recyclerGalery.layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        recyclerGalery.adapter = GaleryAdapter()
        recyclerGalery.addItemDecoration(SpacesItemDecoration(3))
    }
}
