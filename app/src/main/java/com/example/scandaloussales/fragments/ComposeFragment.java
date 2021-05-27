//1DDD. created composegragment and its xml

package com.example.scandaloussales.fragments;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

//import com.example.scandaloussales.Post;
import com.example.scandaloussales.MainActivity;
import com.example.scandaloussales.Post;
import com.example.scandaloussales.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.io.Console;
import java.io.File;

import static android.app.Activity.RESULT_OK;
import static com.google.android.gms.location.LocationServices.getFusedLocationProviderClient;

public class ComposeFragment extends Fragment implements OnMapReadyCallback{

    public static final String TAG = "ComposeFragment";

    public static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 47;
    private ImageView ivPostImage;
    private Button btnUploadImage;
    private EditText etProductName;
    private EditText etPrice;
    private EditText etUPC;
    private Button btnPost;
    private EditText etDescription;
    FragmentManager fragmentManager;
    private File photoFile;
    public String photoFileName = "photo.jpg";
    private GoogleMap mMap;
    private double lng;
    private double lat;
    private static final int REQUEST_FINE_LOCATION = 200;
    private Marker userClick;
    private ScrollView mScrollView;
    private SupportMapFragment mapFragment;


    public ComposeFragment() {
        // Required empty public constructor

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentManager = getActivity().getSupportFragmentManager();

        return inflater.inflate(R.layout.fragment_compose, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ivPostImage = view.findViewById(R.id.ivPostImage);
        btnPost = view.findViewById(R.id.btnPost);
        btnUploadImage = view.findViewById(R.id.btnUploadImage);
        etProductName = view.findViewById(R.id.etProductName);
        etPrice = view.findViewById(R.id.etPrice);
        etUPC = view.findViewById(R.id.etUPC);
        etDescription = view.findViewById(R.id.etDescription);
        mScrollView = view.findViewById(R.id.scrollMap); //parent scrollview in xml, give your scrollview id value

        mapFragment = ((SupportMapFragment) this.getChildFragmentManager().findFragmentById(R.id.map));

        mapFragment.getMapAsync(this);

        btnUploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchCamera();
            }
        });

        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itemName = etProductName.getText().toString();
                int price = Integer.parseInt(etPrice.getText().toString());
                long upc = Long.parseLong(etUPC.getText().toString());
                Log.d(TAG, "Product " + itemName + " " + upc);
                String desc = etDescription.getText().toString();

                if (itemName.isEmpty() || etPrice.getText().toString().isEmpty() || etUPC.getText().toString().isEmpty()) {
                    Toast.makeText(getContext(), "All Boxes must be filled", Toast.LENGTH_SHORT).show();
                    return;
                }

                //added upc length checker. upcs are always 12 numbers long.
                if (etUPC.getText().toString().length() != 12) {
                    Toast.makeText(getContext(), "UPC must be 12 digits long. You have " + etUPC.getText().toString().length() + " characters", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (photoFile == null || ivPostImage.getDrawable() == null) {
                    Toast.makeText(getContext(), "There is no image!", Toast.LENGTH_SHORT).show();
                    return;
                }

                ParseUser currentUser = ParseUser.getCurrentUser();
                savePost(itemName, price, upc, currentUser, photoFile, lat, lng, desc);

                Toast.makeText(getContext(), "Post Successfully Created", Toast.LENGTH_SHORT).show();

                //fragmentManager.beginTransaction().replace(R.id.flContainer, new PostsFragment()).commit();
            }
        });


    }

    private boolean checkPermissions() {
        if (ContextCompat.checkSelfPermission(getContext(),
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            requestPermissions();
            return false;
        }
    }

    private void requestPermissions() {
        ActivityCompat.requestPermissions(getActivity(),
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                REQUEST_FINE_LOCATION);
    }

    public File getPhotoFileUri(String fileName) {

        File mediaStorageDir = new File(getContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES), TAG);
        if (!mediaStorageDir.exists() && !mediaStorageDir.mkdirs()){
            Log.d(TAG, "failed to create directory");
        }
        File file = new File(mediaStorageDir.getPath() + File.separator + fileName);

        return file;
    }

    private void launchCamera() {

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        photoFile = getPhotoFileUri(photoFileName);

        Uri fileProvider = FileProvider.getUriForFile(getContext(), "com.codepath.fileprovider.ScandalousSales", photoFile);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileProvider);

        if (intent.resolveActivity(getContext().getPackageManager()) != null) {
            startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                // by this point we have the camera photo on disk
                Bitmap takenImage = BitmapFactory.decodeFile(photoFile.getAbsolutePath());
                // RESIZE BITMAP, see section below
                // Load the taken image into a preview
                ivPostImage.setImageBitmap(takenImage);
            } else { // Result was a failure
                Toast.makeText(getContext(), "Picture wasn't taken!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void savePost(String name, int price, long upc, ParseUser currentUser, File photoFile, double latitude, double longitude, String desc) {
        Post post = new Post();
        post.setItemName(name);
        post.setPrice(price);
        post.setUpc(upc);
        post.setDesc(desc);
        post.setImage(new ParseFile(photoFile));
        post.setUser(currentUser);
        post.put("latitude", latitude);
        post.put("longitude", longitude);
        post.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if(e != null){
                    Log.e(TAG, "Error while saving", e);
                    Toast.makeText(getContext(), "Error while saving!", Toast.LENGTH_SHORT).show();
                }

                Log.i(TAG, "Post save was successful!");
                etProductName.setText("");
                etPrice.setText("");
                etUPC.setText("");
                etDescription.setText("");
                ivPostImage.setImageResource(0);
            }
        });
    }

    public void getLastLocation() {
        // Get last known recent location using new Google Play Services SDK (v11+)
        FusedLocationProviderClient locationClient = getFusedLocationProviderClient(getContext());


        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            Toast.makeText(getContext(), "Permissions Not Granted ", Toast.LENGTH_SHORT).show();
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


    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if(checkPermissions()) {
            googleMap.setMyLocationEnabled(true);
        }

        getLastLocation();

        BitmapDescriptor defaultMarker = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN);

        googleMap.setOnMapClickListener(new OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {

                if(userClick != null)
                    userClick.remove();

                userClick = googleMap.addMarker(new MarkerOptions()
                        .position(latLng)
                        .title("Product Location")
                        .snippet("This is where the product is")
                        .icon(defaultMarker));

                lng = userClick.getPosition().longitude;
                lat = userClick.getPosition().latitude;
            }
        });
    }
}