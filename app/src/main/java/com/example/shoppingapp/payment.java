package com.example.shoppingapp;

import android.os.Bundle;
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

public class payment extends AppCompatActivity {



    TextView number,exp,cvv;
    DatabaseReference db_reff;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_app);

        db_reff = FirebaseDatabase.getInstance().getReference().child("payment");
        db_reff.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String crdcvv = dataSnapshot.child("crdCvv").getValue().toString();
                String crdexp = dataSnapshot.child("crdExp").getValue().toString();
                String crdnum = dataSnapshot.child("crdNumber").getValue().toString();

                cvv.setText(crdcvv);
                exp.setText(crdexp);
                number.setText(crdnum);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        number = (TextView)findViewById(R.id.payment_number);
        exp = (TextView)findViewById(R.id.payment_exp);
        cvv = (TextView)findViewById(R.id.payment_cvv);
        Toast.makeText(getApplicationContext(),"Filled the above filed",Toast.LENGTH_LONG).show();

    }
}
