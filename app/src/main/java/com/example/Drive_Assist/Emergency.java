package com.example.Drive_Assist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Emergency extends AppCompatActivity {
    EditText mcontact1,mcontact2,mcontact3;
    Button mproceedbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency);

        mcontact1 = findViewById(R.id.contact1);
        mcontact2 = findViewById(R.id.contact2);
        mcontact3 = findViewById(R.id.contact3);
        mproceedbutton=findViewById(R.id.Submitbtn);

        mproceedbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Homescreen.class));
            }
        });


    }
}