package com.example.new_shop;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class admin_dashboard extends AppCompatActivity {
    TextView viewtotal,viewsoldtotal;
    Button btnInventory, btnCurrentOrder, btnAddProduct, btnLogout;
    dbmanager dbManager;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        btnInventory = findViewById(R.id.buttonInventory);
        btnCurrentOrder = findViewById(R.id.buttonCurrentOrder);
        btnAddProduct = findViewById(R.id.buttonAddProduct);
        btnLogout = findViewById(R.id.buttonLogout);
        viewtotal = findViewById(R.id.viewtotal_price);
        viewsoldtotal = findViewById(R.id.totalsold);

        dbManager = new dbmanager(this);
        dbManager.open();
        double totalprice = dbManager.calculateTotalPrice();
        int totalsold = dbManager.getTotalSold();
        viewtotal.setText(String.valueOf(totalprice));
        viewsoldtotal.setText(String.valueOf(totalsold));

        //Inventory Button
        btnInventory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(admin_dashboard.this, admin_inventory.class);
                startActivity(intent);
            }
        });

        //Current Order
        btnCurrentOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(admin_dashboard.this, admin_view_current_order.class);
                startActivity(intent);
            }
        });

        //Admin add product
        btnAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(admin_dashboard.this , admin_add_product_page.class);
                startActivity(intent);
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(admin_dashboard.this ,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}