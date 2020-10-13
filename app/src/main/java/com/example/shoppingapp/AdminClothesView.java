package com.example.shoppingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shoppingapp.Model.Clothes;
import com.example.shoppingapp.ViewContent.ClothesView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class AdminClothesView extends AppCompatActivity {

    private DatabaseReference DataRef;
    private RecyclerView ClothesList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_clothes_view);

        DataRef = FirebaseDatabase.getInstance().getReference().child("Products");

        ClothesList = findViewById(R.id.adminRecycle);
        ClothesList.setLayoutManager(new LinearLayoutManager(AdminClothesView.this));

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<Clothes> options =
                new FirebaseRecyclerOptions.Builder<Clothes>()
                        .setQuery(DataRef, Clothes.class)
                        .build();

        FirebaseRecyclerAdapter<Clothes, ClothesView> adapter =
                new FirebaseRecyclerAdapter<Clothes, ClothesView>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull ClothesView holder, int position, @NonNull final Clothes model) {

                        holder.txtClothesName.setText(model.getName());
                        holder.txtClothesDesc.setText(model.getDescription());
                        holder.txtClothesPrice.setText("Price: Rs."+ model.getPrice());
                        Picasso.get().load(model.getImage()).into(holder.ClothImag);


                        holder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                Intent intent = new Intent(AdminClothesView.this, AdminMaintainActivity.class);
                                intent.putExtra("pid", model.getPid());
                                startActivity(intent);
                                Toast.makeText(AdminClothesView.this, "Product Selected", Toast.LENGTH_SHORT).show();

                            }
                        });
                    }

                    @NonNull
                    @Override
                    public ClothesView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_view_clothes, parent, false);
                        ClothesView holder  = new ClothesView(view);
                        return holder;
                    }
                };



        ClothesList.setAdapter(adapter);
        adapter.startListening();
    }
}