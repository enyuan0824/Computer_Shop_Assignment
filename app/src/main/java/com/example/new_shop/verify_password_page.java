package com.example.new_shop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class verify_password_page extends AppCompatActivity {

    private dbhelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_password_page);

        dbHelper = new dbhelper(this);

        TextView backToLogin = findViewById(R.id.textViewBackToLogin);
        EditText verifyEmail = findViewById(R.id.editTextTextEmailAddress);
        Button submitEmail = findViewById(R.id.buttonSubmitEmail);

        submitEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = verifyEmail.getText().toString();

                if(isValidEmail(email)){
                    String newPassword = "new1234";

                    boolean isPasswordUpdated = dbHelper.updatePassword(email, newPassword);

                    if(isPasswordUpdated){
                        sendPasswordMessage(email, newPassword);

                        Toast.makeText(verify_password_page.this, "Password updated successfully. Check your messages for the new password.", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(verify_password_page.this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(verify_password_page.this,
                                "Failed to update password", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(verify_password_page.this,
                            "Invalid email", Toast.LENGTH_SHORT).show();
                }
            }
        });

        backToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(verify_password_page.this, login_page.class);
                startActivity(intent);
            }
        });


    }
    private boolean isValidEmail(CharSequence target){
        return (!TextUtils.isEmpty(target) && !Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    private void sendPasswordMessage(String email, String newPassword){
        Toast.makeText(this, "New Password: " + newPassword, Toast.LENGTH_LONG).show();
    }
}