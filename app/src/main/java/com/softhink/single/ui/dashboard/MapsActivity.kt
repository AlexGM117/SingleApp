package com.softhink.single.ui.dashboard

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.softhink.single.ui.base.BaseActivity
import com.softhink.single.R
import com.softhink.single.data.remote.response.UserResponse
import com.softhink.single.ui.registro.Status
import kotlin.random.Random

class MapsActivity : BaseActivity(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener, GoogleMap.OnMapClickListener {

    private lateinit var mMap: GoogleMap
    private val mViewModel: MapViewModel by lazy {
        ViewModelProviders.of(this).get(MapViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        setUpToolbar("", true)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        mViewModel.getListOfUsers()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.style_json))
        val mexicoCity = LatLng(19.429507, -99.163656)
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mexicoCity, 12f))

        mMap.setOnMarkerClickListener(this)
        mMap.setOnMapClickListener(this)

        mViewModel.getListResponse().observe(this, Observer {
            when(it.status){
                Status.SUCCESS -> putMarkers(it.data!!)
                Status.ERROR -> errorDialog(it.message)
                Status.FAILED -> errorDialog(it.message)
            }
        })
    }

    private fun putMarkers(data: List<UserResponse>) {
        val markers = arrayOf(LatLng(19.427411, -99.166339), LatLng(19.427517, -99.129610), LatLng(19.419569, -99.140644),
                LatLng(19.421987, -99.175018), LatLng(19.398625, -99.160190))
        for (i in data.indices){
            val marker = MarkerOptions()
                    .title(data[i].fullName)
                    .position(markers[Random.nextInt(0, 4)])
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_avatar_mini))

            mMap.addMarker(marker)
        }
    }

    private fun errorDialog(message: String?) {
        showMessageDialog(message!!, positiveClick = {
            finish()
        })
    }

    override fun onMarkerClick(marker: Marker): Boolean {

        return true
    }

    override fun onMapClick(p0: LatLng?) {

    }
}
