package com.example.new_shop;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class edit_address extends AppCompatActivity {

    private EditText editfloor,editbuilding,editregion;
    ImageView back;
    private Button save;
    private TextView _id;
    private dbmanager dbManager;
    private long addressid;
    private String floor,building,region;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_address);

        editfloor=findViewById(R.id.edittextfloor1);
        editbuilding=findViewById(R.id.edittextbuilding1);
        editregion=findViewById(R.id.edittextregion1);
        save=findViewById(R.id.save_address);
        _id=findViewById(R.id.id);
        back=findViewById(R.id.back_to_myaddress);

        dbManager = new dbmanager(this);
        dbManager.open();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            addressid= Long.parseLong(extras.getString("id"));
            building = extras.getString("building");
            floor = extras.getString("floor");
            region = extras.getString("region");

            _id.setText(String.valueOf(addressid));
            editbuilding.setText(building);
            editfloor.setText(floor);
            editregion.setText(region);
        }

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve the edited information from the UI elements
                String editedregion = editregion.getText().toString().trim();
                String editedbuilding = editbuilding.getText().toString().trim();
                String editedfloor = editfloor.getText().toString().trim();

                // Check if the required fields are not empty
                if (!editedregion.isEmpty() && !editedbuilding.isEmpty()&& !editedfloor.isEmpty()) {
                    // Update the product information in the database
                    int rowsAffected = dbManager.updateaddress(
                            addressid,editedfloor,editedbuilding,editedregion

                    );

                    // Check if the update was successful
                    if (rowsAffected > 0) {
                        Toast.makeText(edit_address.this, "Product information updated!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(edit_address.this, Profile.class);
                        startActivities(new Intent[]{intent});

                    } else {
                        Toast.makeText(edit_address.this, "Failed to update product information.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(edit_address.this, "Please enter product name and price.", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }
}