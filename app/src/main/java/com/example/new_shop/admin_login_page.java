package com.example.new_shop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class admin_login_page extends AppCompatActivity {
    TextInputEditText adminUsername,adminPassword;
    TextInputLayout adminUsernameLayout,adminPasswordLayout;

    Button adminLogin;

    private  dbmanager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login_page);

        adminUsername = findViewById(R.id.TextInputEditTextUsername);
        adminUsernameLayout = findViewById(R.id.TextInputLayoutUsername);
        adminPassword = findViewById(R.id.TextInputEditTextPassword);
        adminPasswordLayout = findViewById(R.id.TextInputLayoutPassword);
        adminLogin = findViewById(R.id.adminLoginButton);

        dbManager = new dbmanager(this);

        adminLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String enteredUsername = adminUsername.getText().toString().trim();
                String enteredPassword = adminPassword.getText().toString().trim();

                // Validate input
                if (enteredUsername.isEmpty() || enteredPassword.isEmpty()) {
                    // Show an error message if fields are empty
                    Toast.makeText(admin_login_page.this, "Username and password cannot be empty", Toast.LENGTH_SHORT).show();
                    return; // Exit the method to prevent further execution
                }

                // Check admin credentials
                if (enteredUsername.equals("sunny") && enteredPassword.equals("admin0123")) {
                    // Credentials are valid, proceed to the admin dashboard
                    Intent intent = new Intent(admin_login_page.this, admin_dashboard.class);
                    startActivity(intent);
                } else {
                    // Invalid credentials, show an error message
                    Toast.makeText(admin_login_page.this, "Invalid admin credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbManager.close();
    }
}