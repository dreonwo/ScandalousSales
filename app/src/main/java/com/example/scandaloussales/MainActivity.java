package com.example.scandaloussales;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Post post = new Post();
//
//        post.setItemName("Chocolate");
//        post.setPrice(23);
//        post.setUpc(1339270837);
//        post.setUser(ParseUser.getCurrentUser());
//
//        post.saveInBackground(new SaveCallback() {
//            @Override
//            public void done(ParseException e) {
//                if(e != null){
//                    Log.e("MainActivity", "Error while saving", e);
//                    Toast.makeText(MainActivity.this, "Error while saving!", Toast.LENGTH_SHORT).show();
//                }
//
//                Log.i("MainActivity", "Post save was successful!");
//            }
//        });
    }
}