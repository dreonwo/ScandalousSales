package com.example.scandaloussales;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.example.scandaloussales.fragments.LogOutFragment;

import android.view.MenuItem;

import com.example.scandaloussales.fragments.ComposeFragment;
import com.example.scandaloussales.fragments.PostsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    //private Button btnScanItem;

    public static final String TAG = "MainActivity";


    final FragmentManager fragmentManager = getSupportFragmentManager();
    private BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        bottomNavigationView = findViewById(R.id.bottomNavigation);


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment;
                switch (menuItem.getItemId()) {
                    case R.id.action_home:
                        //Toast.makeText(MainActivity.this, "Home!", Toast.LENGTH_SHORT).show();
                        fragment = new PostsFragment();
                        fragmentManager.beginTransaction().replace(R.id.flContainer, fragment, "home").commit();
                        break;
                    case R.id.action_compose:
                        //Toast.makeText(MainActivity.this, "Compose!", Toast.LENGTH_SHORT).show();
                        fragment = new ComposeFragment();
                        fragmentManager.beginTransaction().replace(R.id.flContainer, fragment, "compose").commit();

                        break;
                    case R.id.action_log_out:
                        fragment = new LogOutFragment();
                        fragmentManager.beginTransaction().replace(R.id.flContainer, fragment).commit();

                        break;
                    default:
                }
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
    /*public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu; this adds items to the aciton bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);

        //Initialize Menu Inflater
        MenuInflater menuInflater = getMenuInflater();
        //Inflate Menu
        //Initialized Menu Item
        MenuItem menuItem;
        menuItem = menu.findItem(R.id.search_view);

        android.widget.SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);

        //Initialized Search View
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //Filter Array List
                //PostsAdapter.getFilter().filter(newText);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

     */
}