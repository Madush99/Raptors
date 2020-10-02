package com.example.shoppingapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shoppingapp.Model.Cart;
import com.example.shoppingapp.Prevalent.Prevalent;
import com.example.shoppingapp.ViewContent.CartViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class wishList extends AppCompatActivity
{

    ImageView wishHome,wishList,wishBag,wishMe;

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private Button go_shoppingButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wish_list);

        recyclerView = findViewById(R.id.w_list);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        wishHome = (ImageView) findViewById(R.id.homeWish);
        wishList = (ImageView) findViewById(R.id.wishListImg);
        wishBag = (ImageView) findViewById(R.id.WishbagImg);
        wishMe = (ImageView) findViewById(R.id.WishmeImg);

        wishHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(wishList.this,MainActivity.class);
                startActivity(intent);
            }
        });

        wishList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(wishList.this,wishList.class);
                startActivity(intent);
            }
        });

        wishBag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(wishList.this,clothes_cart.class);
                startActivity(intent);
            }
        });

        wishMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(wishList.this,controlPanel.class);
                startActivity(intent);
            }
        });




    }

    @Override
    protected void onStart()
    {
        super.onStart();

        final DatabaseReference wishListRef = FirebaseDatabase.getInstance().getReference().child("Wish List");

        FirebaseRecyclerOptions<Cart> options = new FirebaseRecyclerOptions.Builder<Cart>()
                .setQuery(wishListRef.child("User View")
                        .child(Prevalent.currentOnlineUser.getPhone()).child("Products"), Cart.class)
                .build();

        FirebaseRecyclerAdapter<Cart, CartViewHolder> adapter = new FirebaseRecyclerAdapter<Cart, CartViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull CartViewHolder holder, int position, @NonNull final Cart model)
            {

                holder.txtProductPrice.setText(model.getPrice());
                holder.txtProductName.setText(model.getPname());

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view)
                    {
                        CharSequence options[] = new CharSequence[]
                                {
                                        "Delete"

                                };
                        AlertDialog.Builder builder= new AlertDialog.Builder(wishList.this);
                        builder.setTitle("Wish List Options");

                        builder.setItems(options, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i)
                            {
                                if (i==0)
                                {
                                    Intent intent = new Intent(wishList.this, clothes_profile.class);
                                    intent.putExtra("pid",model.getPid());
                                    startActivity(intent);
                                }

                                if (i==1)
                                {
                                    wishListRef.child("User View").child(Prevalent.currentOnlineUser.getPhone()).child("Products").child(model.getPid())
                                            .removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task)
                                        {
                                            if (task.isSuccessful())
                                            {
                                                Toast.makeText(wishList.this, "Item removed successfully", Toast.LENGTH_SHORT).show();

                                                Intent intent = new Intent(wishList.this, MainActivity.class);

                                                startActivity(intent);
                                            }


                                        }
                                    });

                                }

                            }
                        });

                        builder.show();

                    }
                });

            }

            @NonNull
            @Override
            public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
            {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.wishlist_items, parent, false);
                CartViewHolder holder = new CartViewHolder(view);
                return holder;
            }
        };

        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }




}

