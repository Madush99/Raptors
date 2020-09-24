package com.example.shoppingapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.jar.Attributes;

public class signup extends AppCompatActivity
{

    private Button CreateAccountButton;
    private EditText InputName, InputEmail, InputPhoneNumber, InputPassword;
    private ProgressDialog loadingBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        CreateAccountButton = (Button) findViewById(R.id.signup_btn);
        InputName = (EditText) findViewById(R.id.signup_name_input);
        InputEmail = (EditText) findViewById(R.id.signup_email_address_input);
        InputPhoneNumber = (EditText) findViewById(R.id.signup_phonenumber_input);
        InputPassword = (EditText) findViewById(R.id.signup_password_input);
        loadingBar = new ProgressDialog(this);


        CreateAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateAccount();
                
            }
        });
    }

    private void CreateAccount()
    {
        String name = InputName.getText().toString();
        String email = InputEmail.getText().toString();
        String phone = InputPhoneNumber.getText().toString();
        String password = InputPassword.getText().toString();

        if(TextUtils.isEmpty(name))
        {
            Toast.makeText(this, "Please write your name.", Toast.LENGTH_SHORT).show();
        }

        if(TextUtils.isEmpty(email))
        {
            Toast.makeText(this, "Please write your email.", Toast.LENGTH_SHORT).show();
        }

        if(TextUtils.isEmpty(phone))
        {
            Toast.makeText(this, "Please write your phone number.", Toast.LENGTH_SHORT).show();
        }

        if(TextUtils.isEmpty(password))
        {
            Toast.makeText(this, "Please write your password.", Toast.LENGTH_SHORT).show();
        }

        else
        {
            loadingBar.setTitle("Create Account");
            loadingBar.setMessage("Please wait,while we are checking the credentials");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            ValidatePhoneNumber(name,phone,email,password);
        }

    }

    private void ValidatePhoneNumber(final String name, final String phone,final String email,final String password)
    {
       final DatabaseReference RootRef;
       RootRef = FirebaseDatabase.getInstance().getReference();

       RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot dataSnapshot)
           {
                if (!(dataSnapshot.child("Users").child(phone).exists()))
                {

                    HashMap<String,Object> userDatamap = new HashMap<>();
                    userDatamap.put("name",name);
                    userDatamap.put("email",email);
                    userDatamap.put("phone",phone);
                    userDatamap.put("password",password);

                    RootRef.child("Users").child(phone).updateChildren(userDatamap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task)
                                {
                                 if(task.isSuccessful())
                                 {
                                     Toast.makeText(signup.this, "Congratulations !!! Your account created successfully", Toast.LENGTH_LONG).show();
                                     loadingBar.dismiss();

                                     Intent intent = new Intent(signup.this, login.class);
                                     startActivity(intent);
                                 }

                                 else
                                 {
                                     loadingBar.dismiss();
                                     Toast.makeText(signup.this, "Network Error!!! try again", Toast.LENGTH_SHORT).show();

                                 }
                                }
                            });

                }

                else
                {
                    Toast.makeText(signup.this, ""+phone+"already exists", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                    Toast.makeText(signup.this, "Please try again using another phone number", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(signup.this, startup.class);
                    startActivity(intent);

                }
           }

           @Override
           public void onCancelled(@NonNull DatabaseError databaseError)
           {

           }
       });
    }
}
