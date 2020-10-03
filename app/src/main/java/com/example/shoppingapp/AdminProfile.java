package com.example.shoppingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.shoppingapp.Model.Admins;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AdminProfile extends AppCompatActivity {

    private TextView adminName,adminPhone;
    private EditText adminPass;
    private Button save;
    private ImageView userhome,userprofile;
    DatabaseReference dbSave;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_details);

        adminName = (TextView)findViewById(R.id.adminName);
        adminPhone = (TextView) findViewById(R.id.adminPhone);
        adminPass = (EditText)findViewById(R.id.adminPass);
        save = (Button)findViewById(R.id.passUpdate);
        userhome = (ImageView) findViewById(R.id.UserHome);
        userprofile = (ImageView) findViewById(R.id.UserProfile2);

        final Admins admin = new Admins();

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbSave = FirebaseDatabase.getInstance().getReference().child("Admins");
                dbSave.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.hasChild("12345")){
                            admin.setName(adminName.getText().toString());
                            admin.setPhone(adminPhone.getText().toString());
                            admin.setPassword(adminPass.getText().toString());
                            dbSave = FirebaseDatabase.getInstance().getReference().child("Admins").child("12345");
                            dbSave.setValue(admin);
                            //adminControls();
                            Toast.makeText(AdminProfile.this, "Password Updated Successfully..", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(AdminProfile.this,AdminProfile.class);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(AdminProfile.this, "Invalid", Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


            }
        });

        userhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminProfile.this, AdminMenu.class);
                startActivity(intent);
            }
        });

        userprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminProfile.this, AdminProfile.class);
                startActivity(intent);

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child("Admins").child("12345");
        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChildren()){
                    adminName.setText(snapshot.child("name").getValue().toString());
                    adminPhone.setText(snapshot.child("phone").getValue().toString());
                    adminPass.setText(snapshot.child("password").getValue().toString());
                }
                else {
                    Toast.makeText(getApplicationContext(),"No Source to Display", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}

