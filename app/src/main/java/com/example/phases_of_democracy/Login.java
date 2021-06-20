package com.example.phases_of_democracy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
    private EditText email;
    private EditText password;
    private Button login;
    private TextView register;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email=(EditText) findViewById(R.id.email);
        password=(EditText)findViewById(R.id.password);
        login=(Button) findViewById(R.id.login);
        register=(TextView) findViewById(R.id.register);
        progressBar=(ProgressBar) findViewById(R.id.progressBar);
        mAuth=FirebaseAuth.getInstance();
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this,Register.class));
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String useremail=email.getText().toString().trim();
                String userpass=password.getText().toString().trim();

                if(useremail.isEmpty())
                {
                    email.setError("Please enter email");
                    email.requestFocus();
                    return;
                }
                if(userpass.isEmpty())
                {
                    password.setError("Please enter password");
                    password.requestFocus();
                    return;
                }

                progressBar.setVisibility(view.VISIBLE);

                mAuth.signInWithEmailAndPassword(useremail,userpass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            startActivity(new Intent(Login.this,LandingPage.class));
                        }
                        else
                        {
                            Toast.makeText(Login.this,"Failed login",Toast.LENGTH_SHORT).show();
                        }
                    }
                });


            }
        });
    }
}