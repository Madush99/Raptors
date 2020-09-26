package com.example.shoppingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

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

    ImageView home1,me1,bag1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_view);


        ClothesRef = FirebaseDatabase.getInstance().getReference().child("Products");

        recyclerView = findViewById(R.id.recycler_menu);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        home1 = (ImageView) findViewById(R.id.shop1);
        me1 = (ImageView) findViewById(R.id.me1);

        home1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewClothes.this,MainActivity.class);
                startActivity(intent);
            }
        });

        me1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewClothes.this,controlPanel.class);
                startActivity(intent);
            }
        });



    }

    @Override
    protected void onStart() {

        final String cat = getIntent().getStringExtra("Category");
        super.onStart();

        FirebaseRecyclerOptions<Clothes> options =
              new FirebaseRecyclerOptions.Builder<Clothes>()
                      .setQuery(ClothesRef.orderByChild("category").equalTo(cat), Clothes.class)
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

                                    Intent intent = new Intent(ViewClothes.this, clothes_profile.class);
                                    intent.putExtra("pid", model.getPid());
                                    startActivity(intent);

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