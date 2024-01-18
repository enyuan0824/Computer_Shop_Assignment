package com.example.new_shop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class Select_address extends AppCompatActivity {

    RecyclerView recyclerView;

    private List<Address> addressList;
    addressviewadapter adapter;
    ImageView back;

    dbmanager dbManager;
    @SuppressLint({"MissingInflatedId", "Range"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_address);

        recyclerView = findViewById(R.id.recycle_view_address_2);
        dbManager = new dbmanager(this);
        dbManager.open();

        SharedPreferences sharedPreferences = getSharedPreferences("username", Context.MODE_PRIVATE);
        String name = sharedPreferences.getString("key", "Default Value");

        Cursor cursor = dbManager.getaddress(name);
        addressList = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                Address address = new Address();
                address.setFloor(cursor.getString(cursor.getColumnIndex(dbhelper.FLOOR)));
                address.setBuilding(cursor.getString(cursor.getColumnIndex(dbhelper.BUILDING)));
                address.setRegion(cursor.getString(cursor.getColumnIndex(dbhelper.REGION)));
                addressList.add(address);
            } while (cursor.moveToNext());
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter= new addressviewadapter(Select_address.this,addressList);
        recyclerView.setAdapter(adapter);

        back= findViewById(R.id.back_to_check_out_page);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Select_address.this, Check_out.class);
                startActivity(intent);
            }
        });
    }
}