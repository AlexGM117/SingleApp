package com.softhink.single.dashboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.core.view.isVisible
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.softhink.single.R
import com.softhink.single.SingleHeaderAdapter
import kotlinx.android.synthetic.main.activity_maps.*
import kotlinx.android.synthetic.main.botom_sheet.*


class MapsActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener, GoogleMap.OnMapClickListener {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        val strings: MutableList<String> = mutableListOf("", "", "", "", "", "", "", "", "", "")
        val adapter = SingleHeaderAdapter(this, R.layout.single_header, strings)
        scrollView_header.setAdapter(adapter, strings)
        scrollView_header.setCenter(0, adapter)

        val bottomSheetBehavior = BottomSheetBehavior.from(singleBottomSheet)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_DRAGGING
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN

        bottomSheetBehavior.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback(){
            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                animateBottomSheetArrow(slideOffset)
            }

            override fun onStateChanged(bottomSheet: View, state: Int) {
                when(state){
                    BottomSheetBehavior.STATE_DRAGGING ->{
                    }

                    BottomSheetBehavior.STATE_SETTLING -> {
                    }

                    BottomSheetBehavior.STATE_EXPANDED -> {
                        matcher.visibility = View.GONE
                        val layoutParams = LinearLayout.LayoutParams(120, 120)
                        singleImage.layoutParams = layoutParams
                        view1.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                    }

                    BottomSheetBehavior.STATE_COLLAPSED -> {
                        matcher.visibility = View.VISIBLE
                        singleImage.layoutParams = LinearLayout.LayoutParams(440, 440)
                        view1.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 700)
                    }

                    BottomSheetBehavior.STATE_HIDDEN -> {
                    }

                    BottomSheetBehavior.STATE_HALF_EXPANDED -> {
                    }
                }
            }
        })
    }

    private fun animateBottomSheetArrow(offset: Float) {
        val slideOffset = String.format("%.1f", offset).toFloat()
        swipeView.rotation = slideOffset * 180
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.style_json))

        val mexicoCity = LatLng(19.429507, -99.163656)

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mexicoCity, 12f))

        val markers = arrayOf(LatLng(19.427411, -99.166339), LatLng(19.427517, -99.129610), LatLng(19.419569, -99.140644),
                LatLng(19.421987, -99.175018), LatLng(19.398625, -99.160190))
        for (lats in markers){
            val marker = MarkerOptions()
                    .position(lats)
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.avatar_mini))

            mMap.addMarker(marker)
        }

        mMap.setOnMarkerClickListener(this)
        mMap.setOnMapClickListener(this)
    }

    override fun onMarkerClick(marker: Marker?): Boolean {
        scrollView_header.visibility = View.VISIBLE
        singleBottomSheet.visibility = View.VISIBLE
        return true
    }

    override fun onMapClick(p0: LatLng?) {
        if (scrollView_header.isVisible && singleBottomSheet.isVisible){
            scrollView_header.visibility = View.GONE
            singleBottomSheet.visibility = View.GONE
        }
    }
}
