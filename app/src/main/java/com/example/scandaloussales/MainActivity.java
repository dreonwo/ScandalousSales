package com.example.scandaloussales;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.scandaloussales.fragments.ComposeFragment;
import com.example.scandaloussales.fragments.PostsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    //private Button btnScanItem;


    public static final String TAG = "MainActivity";
    //1FFF. WARNING: MOVED capture image axtivity request code, etDescription,
    //btncaptureimage, ivpostimage, btnsubmit, photoFile and photoFileName to composefragment

    final FragmentManager fragmentManager = getSupportFragmentManager();
    //1BBB.
    private BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //getSupportActionBar().setTitle("Community's Scanned Items");

        bottomNavigationView = findViewById(R.id.bottomNavigation);

        //1GGG. moved btncaptureimage & btnsubmit methods to composefragment

        //1CCC. add a click listener on bottom navigatiuon view
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                //1DDD. added from 'using with fragments' portion of bottom nav view guide
                Fragment fragment;
                switch (menuItem.getItemId()) {
                    case R.id.action_home:
                        //Toast.makeText(MainActivity.this, "Home!", Toast.LENGTH_SHORT).show();
                        fragment = new PostsFragment();
                        break;
                    case R.id.action_compose:
                        //Toast.makeText(MainActivity.this, "Compose!", Toast.LENGTH_SHORT).show();
                        fragment = new ComposeFragment();
                        break;
                    case R.id.action_profile:
                    default:
                        //Toast.makeText(MainActivity.this, "Profile!", Toast.LENGTH_SHORT).show();
                        fragment = new ComposeFragment();
                        break;
                }
                fragmentManager.beginTransaction().replace(R.id.flContainer, fragment).commit();
                return true;
            }
        });

        //Set default selection
        bottomNavigationView.setSelectedItemId(R.id.action_home);


        /*
        btnScanItem = findViewById(R.id.btnScanItem);

        btnScanItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ComposeFragment.class);
                startActivity(i);
            }
        });
        */



    }

    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu; this adds items to the aciton bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}