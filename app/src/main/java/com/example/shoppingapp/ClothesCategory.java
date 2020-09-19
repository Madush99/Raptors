package com.example.shoppingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
                intent.putExtra("category","tops" );
                startActivity(intent);

            }
        });

        tshirts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ClothesCategory.this,AddClothes.class);
                intent.putExtra("category", "tshirts");
                startActivity(intent);
            }
        });

        denims.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ClothesCategory.this,AddClothes.class);
                intent.putExtra("category", "denims");
                startActivity(intent);

            }
        });

        skirts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ClothesCategory.this,AddClothes.class);
                intent.putExtra("category", "skirts");
                startActivity(intent);
            }
        });

        dress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ClothesCategory.this,AddClothes.class);
                intent.putExtra("category", "dress");
                startActivity(intent);
            }
        });
    }
}