package com.example.new_shop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class signup_page extends AppCompatActivity {
    private dbmanager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_page);

        dbManager = new dbmanager(this);

        final TextInputEditText inputEditTextName = findViewById(R.id.editTextName);
        final TextInputEditText inputEditTextEmail = findViewById(R.id.editTextEmail);
        final TextInputEditText inputEditTextPassword = findViewById(R.id.editTextPassword);
        Button signup = findViewById(R.id.buttonSignup);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = inputEditTextName.getText().toString().trim();
                String email = inputEditTextEmail.getText().toString().trim();
                String password = inputEditTextPassword.getText().toString().trim();

                if(!name.isEmpty() && !email.isEmpty() && !password.isEmpty()){
                    if (password.length() > 6) {
                        //open database
                        dbManager.open();

                        //insert data
                        dbManager.insert(name, email, password);

                        //close database
                        dbManager.close();

                        Toast.makeText(signup_page.this, "Sign up successful!", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(signup_page.this,MainActivity.class);
                        startActivity(intent);
                        finish();
                    }else{
                        Toast.makeText(signup_page.this, "Password must more than 6 characters!", Toast.LENGTH_SHORT).show();
                    }
                } else{
                    Toast.makeText(signup_page.this, "Please enter all data!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}