package com.example.shoppingapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.shoppingapp.Prevalent.Prevalent;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class payment_report extends AppCompatActivity {


    private TextView price,description,fname,deli_address;
    DatabaseReference db_reff;
    Button done;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirm_payment_app);

       price = (TextView) findViewById(R.id.price);
       description = findViewById(R.id.p_description);
       deli_address = findViewById(R.id.deli_address);
       fname = (TextView) findViewById(R.id.f_name);
       done = (Button)findViewById(R.id.end_process);

        String F_tot = getIntent().getStringExtra("amount");
        String firstname =getIntent().getStringExtra("cus_name");
        String lasttname =getIntent().getStringExtra("cuslname");
        String street =getIntent().getStringExtra("street");
        String city =getIntent().getStringExtra("city");
        String post =getIntent().getStringExtra("postal");
        String phone = getIntent().getStringExtra("phone");


        price.setText("Total price:"+F_tot+".00");
        fname.setText("Name:"+firstname+" "+lasttname);
        description.setText("Deliver Address:"+street+"\n"+
                "                             "+city+"\n"+
                "                              "+post+"\n"+
                "Contact number:"+phone);



        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(),"Order complete",Toast.LENGTH_SHORT).show();
                Intent intent= new Intent(payment_report.this,MainActivity.class);
                startActivity(intent);

            }
        });

    }

       /* db_reff  = FirebaseDatabase.getInstance().getReference();
        Query lastQuery = db_reff.child("Carts List").child("User View").child(Prevalent.currentOnlineUser.getPhone()).child("Products");
        lastQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String message = dataSnapshot.child("pname").getValue().toString();
                description.setText("Item detail:"+message);




            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle possible errors.
            }
        });*/


    }


