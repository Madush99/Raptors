package com.example.shoppingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ClothesCategory extends AppCompatActivity {

    private Button tops,tshirts,denims,skirts,dress;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clothes_category);

        tops = (Button) findViewById(R.id.btntops);
        tshirts = (Button) findViewById(R.id.btntshirts);
        denims = (Button) findViewById(R.id.btndenim);
        skirts = (Button) findViewById(R.id.btnskirts);
        dress = (Button) findViewById(R.id.btndress);



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


    }
}