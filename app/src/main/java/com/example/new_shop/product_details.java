package com.example.new_shop;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class product_details extends AppCompatActivity {

    TextView computername,Price,Details;
    Button buynow;
    ImageView back;

    private String name;
    private String price;
    private String details;

    dbmanager dbManager;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        computername = findViewById(R.id.computer_name);
        Price = findViewById(R.id.rm);
        Details = findViewById(R.id.details);
        buynow = findViewById(R.id.buy_now_button);
        back = findViewById(R.id.back_to_product_homepage);

        findViewById(R.id.back_to_product_homepage).setOnClickListener(v -> finish());

        dbManager = new dbmanager(this);
        dbManager.open();

        SharedPreferences sharedPreferences = getSharedPreferences("product_info", Context.MODE_PRIVATE);

        String name = sharedPreferences.getString("product_name", "Default product_name");
        String price = sharedPreferences.getString("product_price", "Default product_price");
        String details = sharedPreferences.getString("product_details", "Default product_details");

        computername.setText(name);
        Price.setText(price);
        Details.setText(details);

        buynow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(product_details.this, Check_out.class);
                Bundle bundle = new Bundle();
                bundle.putString("name", computername.getText().toString());
                bundle.putString("price",Price.getText().toString());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(product_details.this,product_homePage.class);
                startActivities(new Intent[]{intent});
            }
        });
    }
}