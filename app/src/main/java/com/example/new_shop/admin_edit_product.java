package com.example.new_shop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class admin_edit_product extends AppCompatActivity {
    private EditText editTextProductName, editTextProductPrice, editTextProductDescription;
    private TextView product_id;
    private Button buttonSaveProduct, buttonExit;
    private dbmanager dbManager;
    private long productId;
    private String name,price,details;
    //private int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_edit_product);

        editTextProductName = findViewById(R.id.editTextText_ProductName);
        editTextProductPrice = findViewById(R.id.editTextText_Price);
        editTextProductDescription = findViewById(R.id.editTextText_Description);
        buttonSaveProduct = findViewById(R.id.button_SaveProduct);
        buttonExit = findViewById(R.id.button_ExitUpdateProduct);
        product_id=findViewById(R.id.product_id);

        dbManager = new dbmanager(this);
        dbManager.open();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            productId= Long.parseLong(extras.getString("id"));
            name = extras.getString("name");
            price = extras.getString("price");
            details = extras.getString("details");

            product_id.setText(String.valueOf(productId));
            editTextProductName.setText(name);
            editTextProductPrice.setText(price);
            editTextProductDescription.setText(details);
        }

        /*Intent intent = getIntent();
        String productName = intent.getStringExtra("PRODUCT_NAME");
        String productPrice = intent.getStringExtra("PRODUCT_PRICE");
        String productDescription = intent.getStringExtra("PRODUCT_DESCRIPTION");
        productId = intent.getLongExtra("PRODUCT_ID", 0);


        // Populate UI elements with retrieved information
        editTextProductName.setText(productName);
        editTextProductPrice.setText(productPrice);
        editTextProductDescription.setText(productDescription);

        dbManager = new dbmanager(this);
        dbManager.open();*/
        buttonSaveProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve the edited information from the UI elements
                String editedProductName = editTextProductName.getText().toString().trim();
                String editedProductPrice = editTextProductPrice.getText().toString().trim();
                String editedProductDescription = editTextProductDescription.getText().toString().trim();

                // Check if the required fields are not empty
                if (!editedProductName.isEmpty() && !editedProductPrice.isEmpty()&& !editedProductDescription.isEmpty()) {
                    // Update the product information in the database
                    int rowsAffected = dbManager.updateProductData(
                            productId,
                            editedProductDescription,
                            editedProductName,
                            editedProductPrice

                    );

                    // Check if the update was successful
                    if (rowsAffected > 0) {
                        Toast.makeText(admin_edit_product.this, "Product information updated!", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(admin_edit_product.this, "Failed to update product information.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(admin_edit_product.this, "Please enter product name and price.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        buttonExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(admin_edit_product.this, admin_view_product.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbManager.close();
    }
}