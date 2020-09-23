package com.example.shoppingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class controlPanel extends AppCompatActivity {

    private Button carDetails,changeDetails;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_details);

        carDetails = (Button)findViewById(R.id.add_card_details);
        changeDetails = (Button) findViewById(R.id.change_details);

        carDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(controlPanel.this,addCard.class);
                startActivity(intent);
            }
        });
        changeDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent= new Intent(controlPanel.this,change_card_details.class);
                startActivity(intent);
            }
        });



    }

}
