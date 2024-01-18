package com.example.new_shop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
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

public class My_address extends AppCompatActivity {

    ConstraintLayout add;
    ImageView back;
    private List<Address> addressList;
    RecyclerView recyclerView;
    dbmanager dbManager;
    addressAdapter adapter;


    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_address);

        recyclerView = findViewById(R.id.recycle_view_address);
        dbManager = new dbmanager(this);
        dbManager.open();

        SharedPreferences sharedPreferences = getSharedPreferences("username", Context.MODE_PRIVATE);
        String name = sharedPreferences.getString("key", "Default Value");

        Cursor cursor = dbManager.getaddress(name);
        addressList = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                Address address = new Address();
                address.setId(Long.parseLong(cursor.getString(cursor.getColumnIndex(dbhelper.ADDRESS_ID))));
                address.setBuilding(cursor.getString(cursor.getColumnIndex(dbhelper.BUILDING)));
                address.setFloor(cursor.getString(cursor.getColumnIndex(dbhelper.FLOOR)));
                address.setRegion(cursor.getString(cursor.getColumnIndex(dbhelper.REGION)));
                addressList.add(address);
            } while (cursor.moveToNext());
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter= new addressAdapter(this,addressList);
        recyclerView.setAdapter(adapter);



        add = findViewById(R.id.add_address);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(My_address.this, Add_address.class);
                startActivity(intent);
            }
        });

        back=findViewById(R.id.back_to_profile2);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(My_address.this, Profile.class);
                startActivity(intent);
            }
        });

    }
}