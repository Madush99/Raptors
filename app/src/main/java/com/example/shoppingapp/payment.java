package com.example.shoppingapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
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
    TextView number,exp,cvv,fina_value,fname,lname,street,city,postal,phone;
    DatabaseReference db_reff;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_app);

        pay=findViewById(R.id.payBtn);
        fina_value=findViewById(R.id.TotPayprice);
        fname=findViewById(R.id.f_name);
        lname=findViewById(R.id.L_name);
        number = (TextView)findViewById(R.id.payment_number);
        exp = (TextView)findViewById(R.id.payment_exp);
        cvv = (TextView)findViewById(R.id.payment_cvv);
        final String F_total = getIntent().getStringExtra("Total Price");
        fina_value.setText("Total:Rs."+F_total+".00");
        Toast.makeText(getApplicationContext(),"Fill the above filed",Toast.LENGTH_LONG).show();
        street=findViewById(R.id.payment_street);
        city=findViewById(R.id.payment_city);
        postal=findViewById(R.id.payment_post);
        phone=findViewById(R.id.payment_phone);


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


        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {





                final Loading loading = new Loading(payment.this);
                loading.PaymentLoadingAnimation();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loading.dismissDialog();
                        Intent intent = new Intent(getApplicationContext(), payment_report.class);



                        String firstname = fname.getText().toString();
                        String lastname =lname.getText().toString();
                        String p_street =street.getText().toString();
                        String p_city =city.getText().toString();
                        String p_post =postal.getText().toString();
                        String pay_phone = phone.getText().toString();

                        intent.putExtra("amount",F_total);
                        intent.putExtra("cus_name",firstname);
                        intent.putExtra("cuslname",lastname);
                        intent.putExtra("street",p_street);
                        intent.putExtra("city",p_city);
                        intent.putExtra("postal",p_post);
                        intent.putExtra("phone",pay_phone);
                        startActivity(intent);



                        startActivity(intent);
                    }
                }, 5000);


            }
        });
    }
}
