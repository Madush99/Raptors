package com.example.shoppingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.shoppingapp.Prevalent.Prevalent;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class controlPanel extends AppCompatActivity {

    private Button carDetails,changeDetails,logout,accDetails;
    private ImageView profilepic;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_details);

        carDetails = (Button)findViewById(R.id.add_card_details);
        changeDetails = (Button) findViewById(R.id.change_details);
        accDetails = (Button) findViewById(R.id.account);
        logout = (Button) findViewById(R.id.logoutbtn1);
        profilepic = (ImageView) findViewById(R.id.imageViewProfile);


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

        accDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(controlPanel.this, settingsAct.class);
                startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(controlPanel.this, login.class );
                startActivity(intent);
                finishAffinity();

            }
        });



    }

    @Override
    protected void onStart() {
        super.onStart();

        DatabaseReference UsersRef = FirebaseDatabase.getInstance().getReference().child("Users").child(Prevalent.currentOnlineUser.getPhone());

        UsersRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                if (dataSnapshot.exists())
                {
                    if (dataSnapshot.child("image").exists())
                    {

                        String image = dataSnapshot.child("image").getValue().toString();


                        Picasso.get().load(image).into(profilepic);

                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
