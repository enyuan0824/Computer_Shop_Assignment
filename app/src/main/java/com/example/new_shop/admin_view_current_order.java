package com.example.new_shop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class admin_view_current_order extends AppCompatActivity {
    private List<ViewOrder> orderList;
    RecyclerView recyclerView;
    dbmanager dbManager;
    orderAdapter adapter;
    Button exit;

    @SuppressLint({"Range", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_current_order);

        exit = findViewById(R.id.button_Exit_currentorder);
        recyclerView = findViewById(R.id.recycle_view_Admin_View_CurrentOrder);
        dbManager = new dbmanager(this);

        try {
            dbManager.open();

            Cursor cursor = dbManager.getOrders();
            orderList = new ArrayList<>();

            if (cursor.moveToFirst()) {
                do {
                    ViewOrder order = new ViewOrder();
                    order.setId(cursor.getLong(cursor.getColumnIndex(dbhelper.ORDER_ID)));
                    order.setName(cursor.getString(cursor.getColumnIndex(dbhelper.NAME_ORDER)));
                    //order.setProduct_name(cursor.getString(cursor.getColumnIndex(dbhelper.PRODUCT_ORDER_NAME)));
                    // Add other fields as needed
                    orderList.add(order);

                } while (cursor.moveToNext());
            }

            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            adapter = new orderAdapter(this, orderList);
            recyclerView.setAdapter(adapter);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbManager.close();
        }

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(admin_view_current_order.this, admin_dashboard.class);
                startActivity(intent);
            }
        });
    }
}