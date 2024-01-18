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

public class admin_inventory extends AppCompatActivity {

    RecyclerView recyclerView;
    private List<Product> productlist1;
    productquantityAdapter adapter;
    dbmanager dbManager;
    Button exit;
    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_inventory);

        recyclerView = findViewById(R.id.recycle_view_inventory);
        dbManager = new dbmanager(this);
        dbManager.open();

        Cursor cursor = dbManager.getQuantityProduct();
        productlist1 = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                Product product = new Product();
                product.setProductName(cursor.getString(cursor.getColumnIndex(dbhelper.PRODUCT_NAME)));
                product.setProductQuantity(cursor.getString(cursor.getColumnIndex(dbhelper.PRODUCT_QUANTITY_ENTRY)));
                product.setQuantitysold(cursor.getString(cursor.getColumnIndex(dbhelper.PRODUCT_QUANTITY_SOLD)));
                product.setQuantityremaining(cursor.getString(cursor.getColumnIndex(dbhelper.PRODUCT_QUANTITY_REMAINING)));
                productlist1.add(product);
            } while (cursor.moveToNext());
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter= new productquantityAdapter(this,productlist1);
        recyclerView.setAdapter(adapter);

        exit = findViewById(R.id.button_ExitInventory);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(admin_inventory.this, admin_dashboard.class);
                startActivity(intent);
            }
        });
    }
}