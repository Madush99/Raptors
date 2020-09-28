package com.example.shoppingapp;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button b1,b2,b3,b4,b5,Search;
    ImageView home,me,Bag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1 = (Button) findViewById(R.id.TopsBtn);
        b2 = (Button) findViewById(R.id.TShirtsBtn);
        b3 = (Button) findViewById(R.id.DenimBtn);
        b4 = (Button) findViewById(R.id.DressBtn);
        b5 = (Button) findViewById(R.id.ShortsBtn);
        Search = (Button) findViewById(R.id.btnSearchN);
        home = (ImageView) findViewById(R.id.homeImg);
        me = (ImageView) findViewById(R.id.MeImg);
        Bag = (ImageView) findViewById(R.id.BagImg);


        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
        b5.setOnClickListener(this);

        Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SearchActivity.class);
                startActivity(intent);
            }
        });

        me.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,controlPanel.class);
                startActivity(intent);
            }
        });

        Bag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,clothes_cart.class);
                startActivity(i);
            }
        });



        ImageView imageAnim = findViewById(R.id.imageAnim1);
        AnimationDrawable animationDrawable = (AnimationDrawable)imageAnim.getDrawable();
        animationDrawable.start();

        ImageView imageAnim2 = findViewById(R.id.imageAnim2);
        AnimationDrawable animationDrawable2 = (AnimationDrawable)imageAnim2.getDrawable();
        animationDrawable2.start();


    }

    @Override
    public void onClick(View v) {

        Toast.makeText(this,"Button Selcted",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), ViewClothes.class);

        switch (v.getId()){
            case R.id.TopsBtn:
                String Tops = "tops";
                intent.putExtra("Category",Tops);
                break;
            case R.id.TShirtsBtn:
                String TShirts = "tshirts";
                intent.putExtra("Category",TShirts);
                break;
            case R.id.DenimBtn:
                String Den = "denims";
                intent.putExtra("Category",Den);
                break;
            case R.id.DressBtn:
                String DressN = "dress";
                intent.putExtra("Category",DressN);
                break;
            case R.id.ShortsBtn:
                String ShortsN = "skirts";
                intent.putExtra("Category",ShortsN);
                break;
        }
        startActivity(intent);

    }
}