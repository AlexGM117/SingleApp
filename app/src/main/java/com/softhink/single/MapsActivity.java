package com.softhink.single;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ImageView swipeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        LinearLayout layoutBottomSheet = findViewById(R.id.bottom_sheet);
        final LinearLayout layout = findViewById(R.id.view1);
        swipeView = findViewById(R.id.swipeView);

        final BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(layoutBottomSheet);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_DRAGGING);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);

        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int state) {
//                System.out.println(state);
                switch (state){
                    case BottomSheetBehavior.STATE_EXPANDED:
                        layout.setVisibility(View.GONE);
                        break;

                    case BottomSheetBehavior.STATE_SETTLING:
                        layout.setVisibility(View.VISIBLE);
                        break;

                    case BottomSheetBehavior.STATE_COLLAPSED:
                        layout.setVisibility(View.VISIBLE);
                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                animateBottomSheetArrow(slideOffset);
            }
        });
    }

    private void animateBottomSheetArrow(float slideOffset) {
        swipeView.setRotation(slideOffset * 180);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;



        try {
            mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.style_json));
        }catch (Exception e){
            Log.e("SingleApp", e.getMessage());
        }

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(19.428405, -99.137316);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Single"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 13));
    }
}
