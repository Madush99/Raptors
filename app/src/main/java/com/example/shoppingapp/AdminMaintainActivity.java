package com.example.shoppingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

public class AdminMaintainActivity extends AppCompatActivity {

    private Button applyChanges,DeleteItem;
    private EditText name,price,desc;
    private ImageView imgView;
    private String productID = "";
    private DatabaseReference clothesRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_maintain);

        productID = getIntent().getStringExtra("pid");
        clothesRef = FirebaseDatabase.getInstance().getReference().child("Products").child(productID);

        applyChanges = (Button) findViewById(R.id.btnApply);
        name = (EditText) findViewById(R.id.proName);
        price = (EditText) findViewById(R.id.proPrice);
        desc = (EditText) findViewById(R.id.proDesc);
        imgView = (ImageView) findViewById(R.id.proImage);
        DeleteItem = (Button) findViewById(R.id.btnDelete);

        displaySpecificProInfo();

        applyChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                applyChanges();
            }
        });

        DeleteItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteCloth();
            }
        });
    }

    private void DeleteCloth(){
        FirebaseDatabase.getInstance().getReference().child("Products").child(productID).
        removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Intent intent = new Intent(AdminMaintainActivity.this, AdminClothesView.class);
                startActivity(intent);
                Toast.makeText(AdminMaintainActivity.this, "Item Deleted Sucessfully", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void applyChanges(){
        String Cname = name.getText().toString();
        String Cdesc = desc.getText().toString();
        String Cprice = price.getText().toString();

        if (Cname.equals("")){
            Toast.makeText(this,"Type Clothe Name....",Toast.LENGTH_SHORT).show();
        }
        else if(Cdesc.equals("")){
            Toast.makeText(this,"Type Clothe Description....",Toast.LENGTH_SHORT).show();
        }
        else if(Cprice.equals("")){
            Toast.makeText(this,"Type Clothe Price..",Toast.LENGTH_SHORT).show();
        }else {
            HashMap<String,Object> productMap = new HashMap<>();

            productMap.put("pid",productID);
            productMap.put("description",Cdesc);
            productMap.put("price",Cprice);
            productMap.put("name",Cname);

            clothesRef.updateChildren(productMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(AdminMaintainActivity.this,"Changes Updated Successfully...",Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(AdminMaintainActivity.this, AdminClothesView.class);
                        startActivity(intent);
                    }
                }
            });
        }
    }

    private void displaySpecificProInfo(){
        clothesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()){
                        String cName = dataSnapshot.child("name").getValue().toString();
                        String cPrice = dataSnapshot.child("price").getValue().toString();
                        String cDesc = dataSnapshot.child("description").getValue().toString();
                        String cImg = dataSnapshot.child("image").getValue().toString();

                        name.setText(cName);
                        price.setText(cPrice);
                        desc.setText(cDesc);
                        Picasso.get().load(cImg).into(imgView);

                    }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}