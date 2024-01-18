package com.example.new_shop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class login_page extends AppCompatActivity {
    public TextInputEditText usernameEditText,passwordEditText;
    public TextInputLayout usernameInputLayout,passwordInputLayout;
    public Button login;
    public TextView forgotPassword, adminLogin;
    private dbmanager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        dbManager = new dbmanager(this);

        usernameInputLayout = findViewById(R.id.TextInputLayoutUsername);
        usernameEditText = findViewById(R.id.TextInputEditTextUsername);
        passwordInputLayout = findViewById(R.id.TextInputLayoutPassword);
        passwordEditText = findViewById(R.id.TextInputEditTextPassword);
        login = findViewById(R.id.loginButton);
        adminLogin = findViewById(R.id.textViewAdminLogin);
        forgotPassword = findViewById(R.id.textViewLinkWord);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmation();
            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login_page.this, verify_password_page.class);
                startActivity(intent);
            }
        });

        //admin login textView
        adminLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login_page.this, admin_login_page.class);
                startActivity(intent);
            }
        });
    }

    private void confirmation(){

        String loginUsername = usernameEditText.getText().toString().trim();
        String loginPassword = passwordEditText.getText().toString().trim();

        //open database
        dbManager.open();

        //check the username and password from database
        if(dbManager.checkUserCredentials(loginUsername, loginPassword)){
            Intent intent = new Intent(login_page.this, product_homePage.class);
            Toast.makeText(this, "Login Success!" , Toast.LENGTH_SHORT).show();

            SharedPreferences sharedPreferences = getSharedPreferences("username", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("key", loginUsername);
            editor.apply();

            startActivity(intent);
            finish();
        } else{
            Toast.makeText(this,"Invalid username or password" , Toast.LENGTH_SHORT).show();
        }
        dbManager.close();
    }
}
