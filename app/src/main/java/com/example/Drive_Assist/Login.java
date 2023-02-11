package com.example.Drive_Assist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Login extends AppCompatActivity {

    public static final String TAG = "TAG";
    EditText memail,mpassword;
    Button mloginbutton;
    TextView noaccount;
    ProgressBar progressBar;
    @Override
    protected   void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_login);

         memail = findViewById(R.id.email);
         mpassword = findViewById(R.id.password);
         mloginbutton = findViewById(R.id.loginbutton);
         progressBar = findViewById(R.id.progressbar);

         mloginbutton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 startActivity(new Intent(getApplicationContext(),Login.class));
             }
         });


    }
}