package com.example.new_shop;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;


public class Check_out extends AppCompatActivity {

    private ActivityResultLauncher<Intent> resultLauncher1;
    private ActivityResultLauncher<Intent> resultLauncher2;
    private ActivityResultLauncher<Intent> resultLauncher3;
    TextView laptopname,price,delivery_fee,total_all,payment,delivery,address;
    ImageView payment_select,delivery_select,address_select,back;
    Button order;


    private String lname,lprice;
    private String ads1,ads2,ads3;
    private dbmanager dbManager;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);

        dbManager = new dbmanager(this);

        laptopname=findViewById(R.id.textviewcomputername);
        price=findViewById(R.id.textviewprice);
        delivery_fee=findViewById(R.id.textviewdelivery_fee);
        total_all=findViewById(R.id.textviewtotal_fee);
        payment=findViewById(R.id.textviewpayment_method);
        delivery=findViewById(R.id.textviewdelivery_option);
        address=findViewById(R.id.textviewaddress);
        payment_select=findViewById(R.id.select_payment_option);
        address_select=findViewById(R.id.select_address);
        delivery_select=findViewById(R.id.select_delivery);
        back=findViewById(R.id.back_to_product_details_page);
        order=findViewById(R.id.button_order);

        SharedPreferences sharedPreferences = getSharedPreferences("product_info", Context.MODE_PRIVATE);

        String name = sharedPreferences.getString("product_name", "Default product_name");
        String product_price = sharedPreferences.getString("product_price", "Default product_price");

        laptopname.setText(name);
        price.setText(product_price);
        delivery_fee.setText("RM 4.90");
        double p = Double.parseDouble(String.valueOf(product_price));
        double total = p+4.90;
        double format_total= Double.parseDouble(String.format("%.2f",total));
        total_all.setText(String.format(String.valueOf(format_total)));

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Check_out.this, product_details.class);
                startActivities(new Intent[]{intent});
            }
        });

        resultLauncher3 = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),result ->{
            if (result.getResultCode() == RESULT_OK){
                Bundle data = result.getData().getExtras();
                if(data != null){
                    ads1 = data.getString("floor");
                    ads2 = data.getString("building");
                    ads3 = data.getString("region");

                    String fullyaddress = ads1 + ", " + ads2 + ", " + ads3 ;
                    address.setText(fullyaddress);
                }
            }
        });
        address_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Check_out.this, Select_address.class);
                resultLauncher3.launch(intent);
            }
        });

        resultLauncher2 = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),result ->{
            if (result.getResultCode() == RESULT_OK){
                Intent data = result.getData();
                if(data != null){
                    String resultText = data.getStringExtra("delivery_option");
                    delivery.setText(resultText);
                }
            }
        });
        delivery_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Check_out.this, Delivery_option.class);
                resultLauncher2.launch(intent);
            }
        });

        resultLauncher1 = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),result ->{
            if (result.getResultCode() == RESULT_OK){
                Intent data = result.getData();
                if(data != null){
                    String resultText = data.getStringExtra("payment_method");
                    payment.setText(resultText);
                }
            }
        });
        payment_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Check_out.this, Payment_method.class);
                resultLauncher1.launch(intent);
            }
        });

        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("username", Context.MODE_PRIVATE);
                String loginUsername = sharedPreferences.getString("key", "Default Value");
                String name = laptopname.getText().toString().trim();
                String py_m = payment.getText().toString().trim();
                String de_o = delivery.getText().toString();
                String to = total_all.getText().toString();

                //open database
                dbManager.open();

                if (!name.isEmpty() && ! py_m.isEmpty() && !de_o.isEmpty())
                {
                    dbManager.insertOrderTable(loginUsername,name,py_m,de_o,to);
                    dbManager.update_quantity_entry(name);
                    Intent intent = new Intent(Check_out.this, payment_details.class);
                    intent.putExtra("payment_name",py_m);
                    startActivity(intent);
                }else {
                    Toast.makeText(Check_out.this, "Please select all option", Toast.LENGTH_SHORT).show();}
                dbManager.close();
            }
        });
    }
}