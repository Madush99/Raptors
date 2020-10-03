package com.example.shoppingapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.shoppingapp.Model.Users;

import com.example.shoppingapp.Prevalent.Prevalent;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class payment extends AppCompatActivity {


    private Button pay;
    TextView number,exp,cvv,fina_value;
    DatabaseReference db_reff;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_app);



        db_reff = FirebaseDatabase.getInstance().getReference().child("payment").child(Prevalent.currentOnlineUser.getPhone());
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
        pay=findViewById(R.id.payBtn);
        fina_value=findViewById(R.id.TotPayprice);
        number = (TextView)findViewById(R.id.payment_number);
        exp = (TextView)findViewById(R.id.payment_exp);
        cvv = (TextView)findViewById(R.id.payment_cvv);
        final String F_total = getIntent().getStringExtra("Total Price");
        fina_value.setText("Total:Rs."+F_total+".00");
        Toast.makeText(getApplicationContext(),"Filled the above filed",Toast.LENGTH_LONG).show();

        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(),"Payment sccuess full",Toast.LENGTH_SHORT).show();


            }
        });
    }
}
