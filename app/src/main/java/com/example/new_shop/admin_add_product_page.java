package com.example.new_shop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class admin_add_product_page extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
    private dbmanager dbManager;
    private ImageView imageView;
    private Uri selectedImageUri;
    private EditText editText_ProductName, editText_Price, editText_QuantityEntry, editText_Discount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_product_page);

        dbManager = new dbmanager(this);

        editText_ProductName = findViewById(R.id.editText_ProductName);
        editText_Price = findViewById(R.id.editText_Price);
        editText_QuantityEntry = findViewById(R.id.editText_QuantityEntry);
        editText_Discount = findViewById(R.id.editText_Discount);

        //Button buttonSelectImage = findViewById(R.id.button_SelectImage);
        Button buttonSaveProduct = findViewById(R.id.button_SaveProduct);
        Button buttonExitAddProduct = findViewById(R.id.button_ExitAddProduct);
        Button buttonViewRecord = findViewById(R.id.button_ViewProductRecord);

        dbManager = new dbmanager(this);
        dbManager.open();

        buttonExitAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(admin_add_product_page.this, admin_dashboard.class);
                startActivity(intent);
            }
        });

        buttonSaveProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveProduct();
            }
        });

        buttonViewRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(admin_add_product_page.this, admin_view_product.class);
               startActivity(intent);
            }
        });
    }

    private void saveProduct(){
        // Get other product details
        String productName = editText_ProductName.getText().toString().trim();
        String price = editText_Price.getText().toString().trim();
        String quantityEntry = editText_QuantityEntry.getText().toString().trim();
        String discount = editText_Discount.getText().toString().trim();

        if(!productName.isEmpty() && !price.isEmpty() && !quantityEntry.isEmpty() && !discount.isEmpty()){

            //insert data
            dbManager.insertProductData(productName, price, quantityEntry, discount );

            Toast.makeText(admin_add_product_page.this, "Insert successful!", Toast.LENGTH_SHORT).show();

            finish();
        } else{
            Toast.makeText(admin_add_product_page.this, "Please enter all data!", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            imageView.setImageURI(selectedImage);
            selectedImageUri = selectedImage;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbManager.close();
    }
}