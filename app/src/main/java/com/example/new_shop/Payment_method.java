package com.example.new_shop;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;

public class Payment_method extends AppCompatActivity {

    ImageView back;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_method);


        RadioGroup pay_option=findViewById(R.id.payment_method);
        back=findViewById(R.id.back_to_check_out1);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Payment_method.this, Check_out.class);
                startActivities(new Intent[]{intent});
            }
        });

        pay_option.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int i)
            {
                String text =null;
                if (i == R.id.ambank){
                    text = "Am Bank";
                }
                else if (i == R.id.hlbank) {
                    text = "Hong Leong Bank";
                }
                else if (i == R.id.pbank) {
                    text = "Public Bank";
                }
                else if (i == R.id.mybank) {
                    text = "MayBank";
                }

                if (text != null) {
                    int code = RESULT_OK; // You can use custom result codes as well
                    Intent data = new Intent();
                    data.putExtra("payment_method", text); // You can include additional data
                    setResult(code, data);
                    finish();
                }
                /*Intent intent = new Intent(Payment_method.this, Check_out.class);
                intent.putExtra("payment_method",text);
                startActivity(intent);*/
            }
        });
    }
}