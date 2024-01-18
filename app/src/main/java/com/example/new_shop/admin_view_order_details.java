package com.example.new_shop;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class admin_view_order_details extends AppCompatActivity {

    TextView username, product,payment, delivery;
    Button exit;

    dbmanager dbManager;

    @SuppressLint({"Range", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_order_details);

        username = findViewById(R.id.textViewUsername);
        product = findViewById(R.id.textViewProduct);
        payment = findViewById(R.id.textViewPayment_Type);
        delivery = findViewById(R.id.textViewDelivery_Option);
        exit = findViewById(R.id.button_Exit_orderdetails);

        Intent intent = getIntent();

        if (intent != null) {
            long orderId = intent.getLongExtra("order_id", -1);

            // Initialize your database manager
            dbManager = new dbmanager(this);
            dbManager.open();

            // Fetch order details from the database based on the order ID
            Cursor cursor = dbManager.getOrderDetails(orderId);
            if (cursor.moveToFirst()) {
                // Update the TextViews with the retrieved order details
                username.setText(cursor.getString(cursor.getColumnIndex(dbhelper.NAME_ORDER)));
                product.setText(cursor.getString(cursor.getColumnIndex(dbhelper.PRODUCT_ORDER_NAME)));
                payment.setText(cursor.getString(cursor.getColumnIndex(dbhelper.PAYMENT_TYPE)));
                delivery.setText(cursor.getString(cursor.getColumnIndex(dbhelper.DELIVERY_OPTION)));
            }

            // Close the cursor and the database manager
            cursor.close();
            dbManager.close();

            exit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(admin_view_order_details.this, admin_view_current_order.class);
                    startActivity(intent);
                }
            });
        }
    }
}