package com.example.shoppingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class ClothesCategory extends AppCompatActivity {

    private Button tops,tshirts,denims,skirts,dress;
    private ImageView catHome, catProfile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clothes_category);

        tops = (Button) findViewById(R.id.btntops);
        tshirts = (Button) findViewById(R.id.btntshirts);
        denims = (Button) findViewById(R.id.btndenim);
        skirts = (Button) findViewById(R.id.btnskirts);
        dress = (Button) findViewById(R.id.btndress);

        catHome = (ImageView) findViewById(R.id.catHome);
        catProfile = (ImageView) findViewById(R.id.catProfile);



        tops.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ClothesCategory.this, AddClothes.class );
                intent.putExtra("category","Tops" );
                startActivity(intent);

            }
        });

        tshirts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ClothesCategory.this,AddClothes.class);
                intent.putExtra("category", "T shirts");
                startActivity(intent);
            }
        });

        denims.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ClothesCategory.this,AddClothes.class);
                intent.putExtra("category", "Denims & Jeans");
                startActivity(intent);

            }
        });

        skirts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ClothesCategory.this,AddClothes.class);
                intent.putExtra("category", "Shorts & Skirts");
                startActivity(intent);
            }
        });

        dress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ClothesCategory.this,AddClothes.class);
                intent.putExtra("category", "Dresses & Codes");
                startActivity(intent);
            }
        });

        catHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ClothesCategory.this, AdminMenu.class);
                startActivity(intent);
            }
        });

        catProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ClothesCategory.this, AdminProfile.class);
                startActivity(intent);
            }
        });

    }
}