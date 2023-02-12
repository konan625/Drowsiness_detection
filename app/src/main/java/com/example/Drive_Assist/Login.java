package com.example.Drive_Assist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    public static final String TAG = "TAG";
    EditText memail,mpassword;
    Button mloginbutton;
    TextView mnoaccountbtn;
    ProgressBar progressBar;

    FirebaseAuth fAuth;
    @Override
    protected   void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_login);

         memail = findViewById(R.id.email);
         mpassword = findViewById(R.id.password);
         mloginbutton = findViewById(R.id.loginbutton);
         progressBar = findViewById(R.id.progressbar);
         mnoaccountbtn = findViewById(R.id.noaccount);
         fAuth = FirebaseAuth.getInstance();



        mnoaccountbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Signuppage.class));
            }
        });
         mloginbutton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 startActivity(new Intent(getApplicationContext(),Emergency.class));
             }


         });

        mloginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = memail.getText().toString().trim();
                String password = mpassword.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    memail.setError("Email is required");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    memail.setError("Password is required");
                    return;
                }

                if(password.length()< 6 ){
                    mpassword.setError("Password must be >= 6 characters");
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);

                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.GONE);
                        if(task.isSuccessful()){
                            Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"error" + task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                startActivity(new Intent(getApplicationContext(), Emergency.class));
            }
        });


    }
}