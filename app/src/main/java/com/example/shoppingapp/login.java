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

import com.example.shoppingapp.Model.Users;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class login extends AppCompatActivity
{
    private EditText InputLoginPhone, InputLoginPassword;
    private Button LoginButton;
    private ProgressDialog loadingBar;

    private String parentDbName = "Users";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        LoginButton = (Button) findViewById(R.id.login_btn);
        InputLoginPhone = (EditText) findViewById(R.id.login_phonenumber_input);
        InputLoginPassword = (EditText) findViewById(R.id.login_password_input);

        loadingBar = new ProgressDialog(this);

        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
             LoginUser();
            }
        });

    }

    private void LoginUser()
    {
        String phone = InputLoginPhone.getText().toString();
        String password = InputLoginPassword.getText().toString();

        if(TextUtils.isEmpty(phone))
        {
            Toast.makeText(this, "Please write your email.", Toast.LENGTH_SHORT).show();
        }

        else if(TextUtils.isEmpty(password))
        {
            Toast.makeText(this, "Please write your password.", Toast.LENGTH_SHORT).show();
        }

        else
        {
            loadingBar.setTitle("Login Account");
            loadingBar.setMessage("Please wait,while we are checking the credentials");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();
            
            AllowAccessToAccount(phone,password);

        }

    }

    private void AllowAccessToAccount(final String phone, final String password)
    {
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                if (dataSnapshot.child(parentDbName).child(phone).exists())
                {
                    Users usersData = dataSnapshot.child(parentDbName).child(phone).getValue(Users.class);

                    if (usersData.getPhone().equals(phone))
                    {

                        if (usersData.getPassword().equals(password))
                        {
                            Toast.makeText(login.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
                            loadingBar.dismiss();

                            Intent intent = new Intent(login.this, startup.class);
                            startActivity(intent);

                        }
                    }

                }

                else
                {
                    Toast.makeText(login.this, "Account "+phone+" dose not exist", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {

            }
        });
    }
}