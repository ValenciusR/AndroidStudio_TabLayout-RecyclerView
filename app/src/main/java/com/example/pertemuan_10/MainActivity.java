package com.example.pertemuan_10;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView registerLink;
    EditText usernameField, passwordField;
    Button loginButton;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameField = findViewById(R.id.et_usernameLogin);
        passwordField = findViewById(R.id.et_passwordLogin);
        loginButton = findViewById(R.id.btn_login);
        registerLink = findViewById(R.id.tv_registerLink);

        sharedPreferences = getSharedPreferences("user_account",MODE_PRIVATE);

        registerLink.setOnClickListener(v->{
            Intent registerIntent = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(registerIntent);
        });

        loginButton.setOnClickListener(v->{
            if(!usernameField.getText().toString().equals(sharedPreferences.getString("username_user",""))){
                Toast.makeText(this, "username tidak ada", Toast.LENGTH_SHORT).show();
            }else if(!passwordField.getText().toString().equals(sharedPreferences.getString("password_user",""))){
                Toast.makeText(this, "password salah", Toast.LENGTH_SHORT).show();
            }else{
                ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
                progressDialog.setTitle("Logging in");
                progressDialog.setMessage("Please Wait...");
                progressDialog.setCancelable(false);
                progressDialog.show();

                new Handler().postDelayed(() ->{
                    progressDialog.dismiss();
                    Intent homeIntent = new Intent(MainActivity.this, HomeActivity.class);
                    homeIntent.putExtra("username_user",sharedPreferences.getString("username_user",""));
                    startActivity(homeIntent);
                },2000);

            }
        });

    }
}