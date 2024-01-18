package com.example.new_shop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class admin_view_product extends AppCompatActivity {

    RecyclerView recyclerView;
    Button back;
    private List<admin_product> productlist;
    //ArrayList<String> product_image, product_name, product_price;
    adminAdapter adapter;
    dbmanager dbManager;
    @SuppressLint({"Range", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_product);

        back=findViewById(R.id.button_ExitProductRecord);
        recyclerView = findViewById(R.id.recycle_view_Admin_View_Product);
        dbManager = new dbmanager(this);
        dbManager.open();

        // Fetch product data from the database
        Cursor cursor = dbManager.getQuantityProduct();
        productlist = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                admin_product product = new admin_product();
                product.setId(Long.parseLong(cursor.getString(cursor.getColumnIndex(dbhelper.PRODUCT_ID))));
                product.setProductName(cursor.getString(cursor.getColumnIndex(dbhelper.PRODUCT_NAME)));
                product.setProductPrice(cursor.getString(cursor.getColumnIndex(dbhelper.PRODUCT_PRICE)));
                product.setDescription(cursor.getString(cursor.getColumnIndex(dbhelper.PRODUCT_DESCRIPTION)));
                productlist.add(product);
            } while (cursor.moveToNext());
        }

        // Set a LinearLayoutManager for the RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize the adapter
        adapter= new adminAdapter(this,productlist);

        // Set the adapter for the RecyclerView
        recyclerView.setAdapter(adapter);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(admin_view_product.this, admin_add_product_page.class);
                startActivity(intent);
            }
        });
    }
}