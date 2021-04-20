package com.example.scandaloussales;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignUpActivity extends AppCompatActivity {

    private EditText etUsername;
    private EditText etPassword;
    private EditText etFirstName;
    private EditText etLastName;
    private Button btnSignup;

    private String tag = "SignUpActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        etUsername = findViewById(R.id.etUPC);
        etPassword = findViewById(R.id.etPassword);
        etFirstName = findViewById(R.id.etFirstName);
        etLastName = findViewById(R.id.etLastName);
        btnSignup = findViewById(R.id.btnSignup);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(tag,"btnSignup Click");

                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                String firstName = etFirstName.getText().toString();
                String lastName = etLastName.getText().toString();

                signUpUser(username, password, firstName, lastName);
            }
        });

    }

    private void signUpUser(String username, String password, String firstName, String lastName){
        ParseUser user = new ParseUser();

        user.setUsername(username);
        user.setPassword(password);
        user.put("firstName", firstName);
        user.put("lastName", lastName);

        user.signUpInBackground(new SignUpCallback() {
            public void done(ParseException e) {
                if (e == null) {
                    goMainActivity();
                    Toast.makeText(SignUpActivity.this,"Account Created!",Toast.LENGTH_SHORT).show();
                } else {
                    Log.e(tag, "Issue with login", e);
                    Toast.makeText(SignUpActivity.this, "Issue with signup",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void goMainActivity() {

        Intent i = new Intent(this, MainActivity.class);

        startActivity(i);
        finish();
    }
}