package com.excelsior.klinik;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity{
    EditText username,email,password,phonenumber;
    Button register, login;
    TextView loginbutton;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        username=findViewById(R.id.username);
        phonenumber=findViewById(R.id.phonenumber);
        login = findViewById(R.id.login_btn_through_register);
        register = findViewById(R.id.register);

        loginbutton=findViewById(R.id.loginbutton);
        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);

            }
        });

        mAuth = FirebaseAuth.getInstance();


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });
    }

    private void registerUser()
    {
        String s_name=username.getText().toString().trim();
        String s_email=email.getText().toString().trim();
        String s_phoneNumber=phonenumber.getText().toString().trim();
        String s_password=password.getText().toString().trim();

        if(s_name.isEmpty())
        {
            username.setError("Name Required");
            username.requestFocus();
            return;
        }

        if(s_email.isEmpty())
        {
            email.setError("Email Required");
            email.requestFocus();
            return;
        }

        if(s_phoneNumber.isEmpty())
        {
            email.setError("Phone Number Required");
            email.requestFocus();
            return;
        }

        if(s_password.isEmpty())
        {
            password.setError("Password Required");
            password.requestFocus();
            return;
        }

        if(s_phoneNumber.length() != 10)
        {
            phonenumber.setError("Enter a valid phone number");
            phonenumber.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(s_email).matches()){
            email.setError("Enter a valid Email address");
            email.requestFocus();
            return;
        }

        if(s_password.length() <6)
        {
            password.setError("Password Required");
            password.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(s_email,s_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    User user = new User(s_name, s_email, s_phoneNumber);

                    FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance()
                            .getCurrentUser().getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(RegisterActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(RegisterActivity.this,MainActivity.class);
                                startActivity(intent);
                            }
                            else{
                                Toast.makeText(RegisterActivity.this,"Registration Failed",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(RegisterActivity.this,task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mAuth.getCurrentUser() != null) {
            Toast.makeText(RegisterActivity.this, "User already exist. you can login", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);

        }
    }
}