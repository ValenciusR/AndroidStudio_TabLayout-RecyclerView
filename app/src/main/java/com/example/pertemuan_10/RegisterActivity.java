package com.example.pertemuan_10;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    TextView loginLink;
    EditText usernameField, emailField, passwordField, confPasswordField;
    Button registerButton;
    SharedPreferences sharedPreferences;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        usernameField = findViewById(R.id.et_usernameRegister);
        emailField = findViewById(R.id.et_emailRegister);
        passwordField = findViewById(R.id.et_passwordRegister);
        confPasswordField = findViewById(R.id.et_confPasswordRegister);
        registerButton = findViewById(R.id.btn_register);
        loginLink = findViewById(R.id.tv_loginLink);

        sharedPreferences = getSharedPreferences("user_account", MODE_PRIVATE);

        loginLink.setOnClickListener(v->{
            Intent loginIntent = new Intent(RegisterActivity.this, MainActivity.class);
            startActivity(loginIntent);
        });

        registerButton.setOnClickListener(v->{
            if(usernameField.getText().toString().length() < 5){
                Toast.makeText(this, "username harus lebih dari 5 karakter", Toast.LENGTH_SHORT).show();
            }else if(!emailField.getText().toString().contains("@") || !emailField.getText().toString().endsWith(".com")){
                Toast.makeText(this, "Email harus ada '@' dan diakhiri dengan '.com'", Toast.LENGTH_SHORT).show();
            }else if(passwordField.getText().toString().length() < 5){
                Toast.makeText(this, "password harus lebih dari 5 karakter", Toast.LENGTH_SHORT).show();
            }else if(!confPasswordField.getText().toString().equals(passwordField.getText().toString())){
                Toast.makeText(this, "Password dan Konfirm Password tidak sama", Toast.LENGTH_SHORT).show();
            }else{
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("username_user",usernameField.getText().toString());
                editor.putString("password_user",passwordField.getText().toString());
                editor.putString("email_user",emailField.getText().toString());
                editor.commit();
                Toast.makeText(this, "Register Success", Toast.LENGTH_SHORT).show();
            }
        });
    }
}