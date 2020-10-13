package com.example.shoppingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AdminMenu extends AppCompatActivity {

    private Button accD, addClothes, maintainClothes, logout;
    private ImageView home,profile;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adminmenu);

        accD = (Button)findViewById(R.id.btnAcc);
        addClothes =(Button)findViewById(R.id.btnAddClothes);
        maintainClothes= (Button)findViewById(R.id.btnMain);
        home = (ImageView) findViewById(R.id.menuHome);
        profile = (ImageView) findViewById(R.id.menuProfile);
        logout = (Button)findViewById(R.id.btnlogout);




        accD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminMenu.this,AdminProfile.class);
                startActivity(intent);
                Toast toast = Toast.makeText(AdminMenu.this, "Welcome To Admin Profile", Toast.LENGTH_SHORT);
                toast.show();
            }
        });


        addClothes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminMenu.this, ClothesCategory.class);
                startActivity(intent);
                Toast toast = Toast.makeText(AdminMenu.this, "You Choose Add Clothes", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        maintainClothes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminMenu.this, AdminClothesView.class);
                startActivity(intent);
                Toast.makeText(AdminMenu.this, "You Choose Maintain Clothes", Toast.LENGTH_SHORT).show();


            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminMenu.this, AdminProfile.class);
                startActivity(intent);
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminMenu.this, AdminMenu.class);
                startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AdminMenu.this,login.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finishAffinity();
                Toast.makeText(AdminMenu.this, "Logged Out Successfully", Toast.LENGTH_SHORT).show();

            }
        });


    }

}
