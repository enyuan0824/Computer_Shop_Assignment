package com.example.new_shop;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Add_address extends AppCompatActivity {

    ImageView back;
    private dbmanager dbManager;
    Button save;
    private EditText building,floor,region;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);

        back=findViewById(R.id.back_to_myaddress);
        building = findViewById(R.id.edittextbuilding);
        floor = findViewById(R.id.edittextfloor);
        region = findViewById(R.id.edittextregion);
        save = findViewById(R.id.save_address);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Add_address.this, My_address.class);
                startActivity(intent);
            }
        });

        dbManager = new dbmanager(this);
        dbManager.open();

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String f = floor.getText().toString();
                String r = region.getText().toString();
                String b = building.getText().toString();
                SharedPreferences sharedPreferences = getSharedPreferences("username", Context.MODE_PRIVATE);
                String name = sharedPreferences.getString("key", "Default Value");

                if(!f.isEmpty() && !r.isEmpty() && !b.isEmpty()){

                    //insert data
                    dbManager.insertaddresstable(name,f,b,r);

                    Toast.makeText(Add_address.this, "Insert successful!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Add_address.this,Profile.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(Add_address.this, "Please enter all data!", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }
}