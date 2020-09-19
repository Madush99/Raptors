package com.example.shoppingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.api.internal.RegisterListenerMethod;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class addPayment extends AppCompatActivity {

    Button add;
    EditText number,exp,cvv,fname,lname,phone,email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_card_details);
    number=(EditText)findViewById(R.id.crd_number);
    exp=(EditText)findViewById(R.id.exp);
    cvv=(EditText)findViewById(R.id.cvv);
    fname=(EditText)findViewById(R.id.f_name);
    lname=(EditText)findViewById(R.id.L_name);
    phone=(EditText)findViewById(R.id.phone);
    email=(EditText)findViewById(R.id.email);

    add=(Button)findViewById(R.id.addBtn);

    add.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            proceessinsert();
        }
    });
    }

    private void proceessinsert(){

        String crdnumber = number.getText().toString();
        String crdexp = exp.getText().toString();
        String crdcvv = cvv.getText().toString();
        String crdfname = fname.getText().toString();
        String crdlname = lname.getText().toString();
        String crdphone = phone.getText().toString();
        String crdemail = email.getText().toString();

        validatePhone(phone);
    }

    private void validatePhone(final EditText phone) {
        this.phone = phone;

        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                if(!(datasnapshot.child("USERS").child(String.valueOf(phone)).exists())){

                    HashMap<String, Object>userdataPay = new HashMap<>();
                    userdataPay.put("number",number);
                    userdataPay.put("exp",exp);
                    userdataPay.put("cvv",cvv);
                    userdataPay.put("fname",fname);
                    userdataPay.put("lname",lname);
                    userdataPay.put("phone",phone);
                    userdataPay.put("email",email);

                    RootRef.child("USERS").child(String.valueOf(phone)).updateChildren(userdataPay)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if(task.isSuccessful()){


                                        Toast.makeText(getApplicationContext(),"complete",Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(addPayment.this,control_panel.class);
                                        startActivity(intent);

                                    }
                                    else{

                                        Toast.makeText(getApplicationContext(),"network error",Toast.LENGTH_SHORT).show();

                                    }
                                }
                            });


                }else{

                    Toast.makeText(getApplicationContext(),"cxvcxvxvcxvxcv",Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}