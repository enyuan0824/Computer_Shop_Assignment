package com.example.new_shop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Profile extends AppCompatActivity {
    private ConstraintLayout order;
    private ConstraintLayout address;
    TextView username;
    ImageView back;
    private TextView back_log_out;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        username=findViewById(R.id.name);
        order= findViewById(R.id.all_order);
        address = findViewById(R.id.my_address);
        back_log_out =  findViewById(R.id.logout);
        back=findViewById(R.id.back_to_product_homepage_1);

        SharedPreferences sharedPreferences = getSharedPreferences("username", Context.MODE_PRIVATE);
        String name = sharedPreferences.getString("key", "Default Value");
        username.setText(name);
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile.this, All_order.class);
                startActivity(intent);
            }
        });

        address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile.this, My_address.class);
                startActivity(intent);
            }
        });

        back_log_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile.this, MainActivity.class);
                startActivity(intent);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile.this,product_homePage.class);
                startActivity(intent);
            }
        });

    }
}