package com.example.login_page;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Signuppage extends AppCompatActivity {

    EditText mFullname,memail,mpassword,mage;
    Button mregisterbutton;
    TextView mloginbutton;
    ProgressBar progressbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_signuppage);

        mFullname = findViewById(R.id.fullname);
        memail = findViewById(R.id.email);
        mpassword = findViewById(R.id.password);
        mage = findViewById(R.id.age);
        mregisterbutton = findViewById(R.id.registerbutton);
        mloginbutton = findViewById(R.id.registerbutton);
        progressbar = findViewById(R.id.progressbar);

        mloginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Login.class));
            }
        });

        mregisterbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email = memail.getText().toString().trim();
                String password = mpassword.getText().toString().trim();
                final String fullname = mFullname.getText().toString();
                final String age = mage.getText().toString();

                if(TextUtils.isEmpty(email)){
                    memail.setError("Email is required");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    mpassword.setError("password is required");
                    return;
                }


            }
        });
    }
}