package com.example.scandaloussales;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuInflater;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.scandaloussales.fragments.ProfileFragment;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;
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

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //private Button btnScanItem;
    ListView listView;

    ArrayList<String> stringArrayList = new ArrayList<>();
    ArrayAdapter<String> adapter;

    public static final String TAG = "MainActivity";


    final FragmentManager fragmentManager = getSupportFragmentManager();
    private BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        listView = findViewById(R.id.list_view);

        for(int i=0; i<=100; i++){
            stringArrayList.add("Item " + i);
        }

        adapter = new ArrayAdapter<>(MainActivity.this
                ,android.R.layout.simple_list_item_1,stringArrayList);


        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Display click item position in toast
                Toast.makeText(getApplicationContext()
                        ,adapter.getItem(position), Toast.LENGTH_SHORT).show();

            }
        });


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
                    case R.id.action_profile:
                        fragment = new ProfileFragment();
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

    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu; this adds items to the aciton bar if it is present.
     //   getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_search,menu);
        MenuItem menuItem = menu.findItem(R.id.search_view);
        android.widget.SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //Filter Array List
                adapter.getFilter().filter(newText);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
}