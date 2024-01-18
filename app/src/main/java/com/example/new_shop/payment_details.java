package com.example.new_shop;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class payment_details extends AppCompatActivity {

    Button pay;
    TextInputEditText usernameedittext,passwordedittext;
    TextInputLayout usernamelayout,passwordlayout;
    TextView bankname;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_details);

        pay = findViewById(R.id.button_pay_now);
        bankname = findViewById(R.id.textview_bank_name);
        usernameedittext = findViewById(R.id.TextInputEditText_online_banking_username);
        passwordedittext = findViewById(R.id.TextInputEditText_online_banking_password);
        usernamelayout = findViewById(R.id.TextInputLayout_online_banking_username);
        passwordlayout = findViewById(R.id.TextInputLayout_online_banking_password);
        Intent intent =getIntent();
        String name = intent.getStringExtra("payment_name");
        bankname.setText(name);


        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!usernameedittext.getText().toString().isEmpty() && !passwordedittext.getText().toString().isEmpty()){
                    Toast.makeText(payment_details.this, "Pay successful!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(payment_details.this, pay_success.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(payment_details.this, "Can no be empty!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}