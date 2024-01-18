package com.example.new_shop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class Delivery_option extends AppCompatActivity {

    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_option);


        RadioGroup de_option=findViewById(R.id.delivery_option);
        back=findViewById(R.id.back_to_check_out);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Delivery_option.this, Check_out.class);
                startActivities(new Intent[]{intent});
            }
        });

        de_option.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int i)
            {
                String text =null;
                if (i == R.id.sd){
                    text = "Standard Delivery";
                }
                else if (i == R.id.jat) {
                    text = "J & T Express";
                }
                else if (i == R.id.pos) {
                    text = "PosLaju Express";
                }
                else if (i == R.id.ninja) {
                    text = "Ninja Van";
                }

                if (text != null) {
                    int code = RESULT_OK; // You can use custom result codes as well
                    Intent data = new Intent();
                    data.putExtra("delivery_option", text); // You can include additional data
                    setResult(code, data);
                    finish();
                }

                /*Intent intent = new Intent(Delivery_option.this, Check_out.class);
                intent.putExtra("delivery_option",text);
                startActivity(intent);*/
            }
        });
    }
}