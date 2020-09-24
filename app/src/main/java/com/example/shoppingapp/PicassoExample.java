package com.example.shoppingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class PicassoExample extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picasso_example);

        ImageView imagPhoto=findViewById(R.id.imgPhoto);
        Picasso.get().load("https://assets.myntassets.com/h_1440,q_100,w_1080/v1/assets/images/5527492/2018/5/7/11525683977921-Navy-Blue--Green-Embroidered-yoke-cape-design-Maxi-2971525683977801-1.jpg").into(imagPhoto);
    }
}