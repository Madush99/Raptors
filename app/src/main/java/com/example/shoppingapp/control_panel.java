package com.example.shoppingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class control_panel extends AppCompatActivity {
    private Button addDetails,editDetail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control_panel);

        addDetails = findViewById(R.id.addDetail);
        editDetail = findViewById(R.id.changeDetails);

        addDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(control_panel.this,addPayment.class);
                startActivity(intent);
            }
        });

        editDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(control_panel.this,edit_deletePayDetails.class);
                startActivity(intent);
            }
        });
    }
}