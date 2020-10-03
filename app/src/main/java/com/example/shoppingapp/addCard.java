package com.example.shoppingapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.shoppingapp.Prevalent.Prevalent;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class addCard extends AppCompatActivity {


    private String crdnumber,crdexp,crdcvv,crdfname, crdlname, crdphone,crdemail,savedDate, saveTime;
    private Button add;
    private EditText Number,Exp,Cvv,Fname, Lname, Phone,Email;
    private DatabaseReference productPAY;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_card_details);

        productPAY = FirebaseDatabase.getInstance().getReference().child("card");
        Number = (EditText)findViewById(R.id.number);
        Exp = (EditText)findViewById(R.id.exp);
        Cvv = (EditText)findViewById(R.id.cvv);
        Fname = (EditText)findViewById(R.id.fname);
        Lname = (EditText)findViewById(R.id.lname);
        Phone = (EditText)findViewById(R.id.phone);
        Email = (EditText)findViewById(R.id.email);
        add = (Button)findViewById(R.id.add);

        crdphone = Prevalent.currentOnlineUser.getPhone();
        Phone.setText(crdphone);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardetailsInsert();
            }
        });
    }

    private void cardetailsInsert() {

        crdnumber = Number.getText().toString();
        crdexp = Exp.getText().toString();
        crdcvv = Cvv.getText().toString();
        crdfname = Fname.getText().toString();
        crdlname = Lname.getText().toString();
        crdemail = Email.getText().toString();




        if(TextUtils.isEmpty(crdnumber)){

            Toast.makeText(this,"Please enter product description..", Toast.LENGTH_SHORT).show();

        }
        else if(TextUtils.isEmpty(crdexp)){

            Toast.makeText(this,"Please enter product price..", Toast.LENGTH_SHORT).show();

        } else if(TextUtils.isEmpty(crdcvv)){

            Toast.makeText(this,"Please enter product price..", Toast.LENGTH_SHORT).show();

        } else if(TextUtils.isEmpty(crdfname)){

            Toast.makeText(this,"Please enter product price..", Toast.LENGTH_SHORT).show();

        } else if(TextUtils.isEmpty(crdlname)){

            Toast.makeText(this,"Please enter product price..", Toast.LENGTH_SHORT).show();

        } else if(TextUtils.isEmpty(crdphone)){

            Toast.makeText(this,"Please enter product price..", Toast.LENGTH_SHORT).show();

        }else if(TextUtils.isEmpty(crdemail)){

            Toast.makeText(this,"Please enter product price..", Toast.LENGTH_SHORT).show();

        }else{

            addprocessing();

        }

    }

    private void addprocessing() {

        HashMap<String,Object> addCrad = new HashMap<>();


        addCrad.put("crdNumber",crdnumber);
        addCrad.put("crdExp",crdexp);
        addCrad.put("crdCvv",crdcvv);
        addCrad.put("crdFname",crdfname);
        addCrad.put("crdLname",crdlname);
        addCrad.put("crdPhone",crdphone);
        addCrad.put("crdEmail",crdemail);




        FirebaseDatabase.getInstance().getReference().child("payment").child(Prevalent.currentOnlineUser.getPhone()).updateChildren(addCrad)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if(task.isSuccessful()){


                            Toast.makeText(getApplicationContext(),"added succesfull",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(addCard.this,controlPanel.class);
                            startActivity(intent);

                        }
                        else{


                                Toast.makeText(getApplicationContext(),"Added failed",Toast.LENGTH_SHORT).show();

                        }

                    }
                });

    }
}
