package com.softhink.single.ui.dashboard

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.softhink.single.ui.base.BaseActivity
import com.softhink.single.R
import com.softhink.single.data.remote.response.UserResponse
import com.softhink.single.ui.dashboard.adapters.SingleHeaderAdapter
import com.softhink.single.ui.registro.Status
import kotlinx.android.synthetic.main.activity_maps.*

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
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.style_json))
        if (checkPermissionGranted(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION))) {
            initMap()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSIONS_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                initMap()
            } else {
                finish()
            }
        }
    }

    override fun onMarkerClick(marker: Marker): Boolean {
        marker.showInfoWindow()
        setVisibilityOfHeader(View.VISIBLE)
        return true
    }

    override fun onMapClick(latLng: LatLng) {
        setVisibilityOfHeader(View.GONE)
    }

    private fun initMap() {
        CurrentLocationListener(this).observe(this, Observer {
            if (it != null) {
                println("Latitud: ${it.latitude}")
                println("Longitud: ${it.longitude}")
                mViewModel.updateLocation(LatLng(it.latitude, it.longitude))
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(it.latitude, it.longitude), 10f))
                mMap.setOnMarkerClickListener(this)
                mMap.setOnMapClickListener(this)
                mMap.setMinZoomPreference(11f)
                mMap.setMaxZoomPreference(14f)
            }
        })

        mViewModel.getListResponse().observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> putMarkers(it.data!!)
                Status.ERROR -> errorDialog(it.message)
                Status.FAILED -> errorDialog(it.message)
            }
        })
    }

    private fun initHeader(singles: List<UserResponse>) {
        singlesHeader.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        singlesHeader.adapter = SingleHeaderAdapter(singles)
    }

    private fun putMarkers(data: List<UserResponse>) {
        for (i in data.indices){
            val marker = MarkerOptions()
                    .title(data[i].username)
                    .position(LatLng(data[i].latitud.toDouble(), data[i].longitud.toDouble()))
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_avatar_mini))
            mMap.addMarker(marker)
        }
        initHeader(data)
    }

    private fun errorDialog(message: String?) {
        showMessageDialog(message)
    }

    private fun setVisibilityOfHeader(visibility: Int) {
        singlesHeader.visibility = visibility
    }
}