package com.example.scandaloussales;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Parcel;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import org.parceler.Parcels;
import org.w3c.dom.Text;

import static com.google.android.gms.location.LocationServices.getFusedLocationProviderClient;

public class ItemDetail extends AppCompatActivity implements OnMapReadyCallback {

    Post post;
    TextView tvProductName;
    TextView tvPrice;
    TextView tvUpc;
    TextView tvUsername;
    ImageView ivItemImage;
    SupportMapFragment mapFragment;
    private GoogleMap mMap;
    private static final int REQUEST_FINE_LOCATION = 200;


    String tag = "ItemDetail";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        post = Parcels.unwrap(getIntent().getParcelableExtra("post"));

        tvProductName =findViewById(R.id.tvProductName);
        tvPrice = findViewById(R.id.tvPrice);
        tvUpc = findViewById(R.id.tvUpc);
        tvUsername = findViewById(R.id.tvUsername);
        ivItemImage = findViewById(R.id.ivItemImage);
        Glide.with(this).load(post.getImage().getUrl()).into(ivItemImage);


            mapFragment = ((SupportMapFragment) this.getSupportFragmentManager().findFragmentById(R.id.map));
            mapFragment.getMapAsync(this);

        tvProductName.setText(post.getItemName());
        tvPrice.setText(""+post.getPrice());
        tvUpc.setText(""+post.getUpc());
        tvUsername.setText(post.getUser().getUsername());

    }

    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        if(checkPermissions()) {
            googleMap.setMyLocationEnabled(true);
        }

        if(post.getDouble("latitude") <= 0.0)
        getLastLocation();

        BitmapDescriptor defaultMarker = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN);

        LatLng latLng = new LatLng(post.getDouble("latitude"), post.getDouble("longitude"));

        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));

        googleMap.addMarker(new MarkerOptions()
                .position(latLng)
                .title("Product Location")
                .snippet("This is where the product is")
                .icon(defaultMarker));


    }

    private boolean checkPermissions() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            requestPermissions();
            return false;
        }
    }

    private void requestPermissions() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                REQUEST_FINE_LOCATION);
    }

    public void getLastLocation() {
        // Get last known recent location using new Google Play Services SDK (v11+)
        FusedLocationProviderClient locationClient = getFusedLocationProviderClient(this);


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            Toast.makeText(this, "Permissions Not Granted ", Toast.LENGTH_SHORT).show();
            // return;
        }

        locationClient.getLastLocation()
                .addOnSuccessListener(new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // GPS location can be null if GPS is switched off
                        if (location != null) {
                            onLocationChanged(location);
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("MapDemoActivity", "Error trying to get last GPS location");
                        e.printStackTrace();
                    }
                });
    }

    public void onLocationChanged(Location location) {
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        mMap.addMarker(new MarkerOptions().position(latLng).title("You are here!"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
    }
}