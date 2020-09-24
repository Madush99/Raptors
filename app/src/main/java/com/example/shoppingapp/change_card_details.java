package com.example.shoppingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class change_card_details extends AppCompatActivity {

    TextView number,exp,cvv,email;
    DatabaseReference db_reff;
    Button Ret;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.remove_update);


                db_reff = FirebaseDatabase.getInstance().getReference().child("payment");
                db_reff.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String crdcvv = dataSnapshot.child("crdCvv").getValue().toString();
                        String crdemail = dataSnapshot.child("crdEmail").getValue().toString();
                        String crdexp = dataSnapshot.child("crdExp").getValue().toString();
                        String crdfname = dataSnapshot.child("crdFname").getValue().toString();
                        String crdlname = dataSnapshot.child("crdLname").getValue().toString();
                        String crdnum = dataSnapshot.child("crdNumber").getValue().toString();
                        String crdphone = dataSnapshot.child("crdPhone").getValue().toString();


                        cvv.setText(crdcvv);
                        email.setText(crdemail);
                        exp.setText(crdexp);
                        number.setText(crdnum);


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                Toast.makeText(getApplicationContext(),"Data fetching",Toast.LENGTH_SHORT).show();

        number = (TextView)findViewById(R.id.r_m_number);
        exp = (TextView)findViewById(R.id.r_m_exp);
        cvv = (TextView)findViewById(R.id.r_m_cvv);
        email = (TextView)findViewById(R.id.r_m_email);
        Ret =(Button)findViewById(R.id.pay_remove);



        Ret.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Object cardnum;
                deleteData();
            }
        });


    }

    private void deleteData() {

        DatabaseReference cardDetails = FirebaseDatabase.getInstance().getReference("payment");
        cardDetails.removeValue();

        Toast.makeText(this, "Card details removed", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(change_card_details.this,addCard.class);
        startActivity(intent);

    }



}
