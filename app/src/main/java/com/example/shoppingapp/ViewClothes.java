package com.example.shoppingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

public class ViewClothes extends AppCompatActivity {

    private DatabaseReference ClothesRef;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    private String type = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_view);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null)
        {
            type = getIntent().getExtras().get("Admin").toString();
        }


        ClothesRef = FirebaseDatabase.getInstance().getReference().child("Products");

        recyclerView = findViewById(R.id.recycler_menu);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);


    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<Clothes> options =
                new FirebaseRecyclerOptions.Builder<Clothes>()
                .setQuery(ClothesRef, Clothes.class)
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

                                if (type.equals("Admin")){
                                    Intent intent = new Intent(ViewClothes.this, AdminMaintainActivity.class);
                                    intent.putExtra("pid", model.getPid());
                                    startActivity(intent);
                                }
                                else{
                                    Intent intent = new Intent(ViewClothes.this, clothes_profile.class);
                                    intent.putExtra("pid", model.getPid());
                                    startActivity(intent);
                                }

//                                Intent intent = new Intent(ViewClothes.this, AdminMaintainActivity.class);
//                                intent.putExtra("pid", model.getPid());
//                                startActivity(intent);
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



        recyclerView.setAdapter(adapter);
        adapter.startListening();

    }


}