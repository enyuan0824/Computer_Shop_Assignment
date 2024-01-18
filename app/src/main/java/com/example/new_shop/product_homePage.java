package com.example.new_shop;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

public class  product_homePage extends AppCompatActivity {

    RecyclerView recyclerView;
    //private ActivityResultLauncher<Intent> resultLauncher1;
    private List<Product> productlist;
    productAdapter adapter;
    dbmanager dbManager;
    ImageButton profile;

    @SuppressLint({"Range", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_home_page);

        recyclerView = findViewById(R.id.recyclerView);
        dbManager = new dbmanager(this);
        dbManager.open();

        // Fetch product data from the database
        Cursor cursor = dbManager.getQuantityProduct();
        productlist = new ArrayList<>();

        /*product_image = new ArrayList<>();
        product_name = new ArrayList<>();
        product_price = new ArrayList<>();
        product_details = new ArrayList<>();*/

        if (cursor != null && cursor.moveToFirst()) {
            do {
                Product product = new Product();
                product.setProductName(cursor.getString(cursor.getColumnIndex(dbhelper.PRODUCT_NAME)));
                product.setProductPrice(cursor.getString(cursor.getColumnIndex(dbhelper.PRODUCT_PRICE)));
                product.setDescription(cursor.getString(cursor.getColumnIndex(dbhelper.PRODUCT_DESCRIPTION)));
                product.setQuantityremaining(cursor.getString(cursor.getColumnIndex(dbhelper.PRODUCT_QUANTITY_REMAINING)));
                productlist.add(product);
            } while (cursor.moveToNext());
        }

        // Set a LinearLayoutManager for the RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize the adapter
        adapter= new productAdapter(this,productlist);

        // Set the adapter for the RecyclerView
        recyclerView.setAdapter(adapter);

        profile=findViewById(R.id.profile_button);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(product_homePage.this, Profile.class);
                startActivities(new Intent[]{intent});
            }
        });

    }
}