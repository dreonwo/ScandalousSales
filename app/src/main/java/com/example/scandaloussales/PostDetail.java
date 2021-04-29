package com.example.scandaloussales;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.scandaloussales.fragments.PostsFragment;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class PostDetail extends AppCompatActivity {

    private SupportMapFragment mapFragment;
    MainActivity ma = new MainActivity();
    FragmentManager fragmentManager = ma.fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_post_detail);

    }

    protected void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mapFragment == null) {
            mapFragment = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map));
            // Check if we were successful in obtaining the map.
            if (mapFragment != null) {
                mapFragment.getMapAsync(new OnMapReadyCallback() {
                    @Override
                    public void onMapReady(GoogleMap map) {
                        loadMap(map);
                    }
                });
            }
        }
    }

    protected void loadMap(GoogleMap googleMap) {
        if (googleMap != null) {
            // ... use map here
            // Set the color of the marker to green
            BitmapDescriptor defaultMarker =
                    BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN);
            // listingPosition is a LatLng point
            LatLng listingPosition = new LatLng(-33.867, 151.206);
            // Create the marker on the fragment
            Marker mapMarker = googleMap.addMarker(new MarkerOptions()
                    .position(listingPosition)
                    .title("Some title here")
                    .snippet("Some description here")
                    .icon(defaultMarker));
        }
    }

    //allows user to press the back button after they view the post detail screen
    @Override
    public void onBackPressed() {
        fragmentManager.beginTransaction().replace(R.id.flContainer, new PostsFragment()).commit();
    }
}



