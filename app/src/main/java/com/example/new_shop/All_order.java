package com.example.new_shop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
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
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class All_order extends AppCompatActivity {

    RecyclerView recyclerView;
    private List<ViewOrder> viewOrderList;
    TextView button,process;
    all_orderAdapter adapter;
    dbmanager dbManager;
    private ImageView back;

    @SuppressLint({"MissingInflatedId", "Range"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_order);

        recyclerView = findViewById(R.id.recycle_view_all_order);
        dbManager = new dbmanager(this);
        dbManager.open();

        SharedPreferences sharedPreferences = getSharedPreferences("username", Context.MODE_PRIVATE);
        String name = sharedPreferences.getString("key", "Default Value");

        // Fetch product data from the database
        Cursor cursor = dbManager.getOrdersbyname(name);
        viewOrderList = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                ViewOrder viewOrder = new ViewOrder();
                viewOrder.setProcess(cursor.getString(cursor.getColumnIndex(dbhelper.PROCESS)));
                viewOrder.setProduct_name(cursor.getString(cursor.getColumnIndex(dbhelper.PRODUCT_ORDER_NAME)));
                viewOrder.setTotal(cursor.getString(cursor.getColumnIndex(dbhelper.TOTAL_PRICE)));
                viewOrderList.add(viewOrder);
            } while (cursor.moveToNext());
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter= new all_orderAdapter(this,viewOrderList);
        recyclerView.setAdapter(adapter);


        /*button= findViewById(R.id.receive);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                process=findViewById(R.id.Process);
                process.setText("Receive");

            }
        });*/

        back= findViewById(R.id.back_to_profile1);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Intent intent = new Intent(All_order.this, Profile.class);
                 startActivities(new Intent[]{intent});
            }
        });
    }
}