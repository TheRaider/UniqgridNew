package com.uniqgrid.solarenergy.uniqgrid;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.HashMap;

public class ProfileActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener  {

    private TextView mPlaceDetailsText;
    private TextView mPlaceAttribution;
    NestedScrollView nsAddEst;
    HashMap<String,Place> markersList = new HashMap<>();



    private GoogleMap mMap;
    private GoogleMap googleMap;

    SupportMapFragment mapFragment;

    MapView mapView;
    Place place = null;

    int PLACE_PICKER_REQUEST = 1;




    FloatingActionButton fabForward;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        overridePendingTransition(R.anim.activity_open_translate,R.anim.activity_close_scale);

        if(getSupportActionBar()!=null){
            getSupportActionBar().setTitle("Profile");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // Retrieve the TextViews that will display details about the selected place.
        mPlaceDetailsText = (TextView) findViewById(R.id.place_details);
        mPlaceAttribution = (TextView) findViewById(R.id.place_attribution);

        nsAddEst = (NestedScrollView) findViewById(R.id.nsAddEst);








        // Solving clash between scrolls of map and scroll view
        final ImageView transparentImageView = (ImageView) findViewById(R.id.transparent_image);

        transparentImageView.setOnTouchListener(
                new View.OnTouchListener() {

                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        int action = event.getAction();
                        switch (action) {
                            case MotionEvent.ACTION_DOWN:
                                // Disallow ScrollView to intercept touch events.
                                nsAddEst.requestDisallowInterceptTouchEvent(true);
                                // Disable touch on transparent view
                                return false;

                            case MotionEvent.ACTION_UP:
                                v.performClick();
                                // Allow ScrollView to intercept touch events.
                                nsAddEst.requestDisallowInterceptTouchEvent(false);
                                return true;

                            case MotionEvent.ACTION_MOVE:
                                nsAddEst.requestDisallowInterceptTouchEvent(true);
                                return false;

                            default:
                                return true;
                        }

                    }
                });




       // Get the SupportMapFragment and request notification
        // when the map is ready to be used.
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }




    public void getLocation(){

        mapFragment.getMapAsync(this);
        setLocation();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        setLocation();
        // Zoom Buttons
       // mMap.getUiSettings().setZoomControlsEnabled(true);
        // Setting Map Type
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

      /* // Requesting Permissions for location
        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){

            mMap.setMyLocationEnabled(true);
            mMap.getUiSettings().setMyLocationButtonEnabled(true);


        }else {
            ActivityCompat.requestPermissions(AddEstablishmentActivity.this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},
                    1);
        }
        */

        mMap.setOnMarkerClickListener(this);

    }



    public void setLocation(){
        Log.d("Location Selected ",place!=null?place.getName().toString():"null");
        if(place !=null) {
            mMap.clear();
            MarkerOptions markerOptions = new MarkerOptions().position(place.getLatLng()).title(place.getName().toString());
            //   markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_trends));
            Marker marker = mMap.addMarker(markerOptions);

            CameraPosition cameraPosition = new CameraPosition.Builder().target(
                    place.getLatLng()).zoom(14).build();
            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return true;
        }
    }

    @Override
    public boolean onMarkerClick(Marker marker) {

        return true;
    }



}
