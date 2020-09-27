package com.example.shoppingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

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

public class SearchActivity extends AppCompatActivity {

    private Button searchBtn;
    private EditText inputText;
    private RecyclerView searchList;
    private String searchInput;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        inputText = findViewById(R.id.searchClothes);
        searchBtn = findViewById(R.id.btnSearch);
        searchList = findViewById(R.id.search_list);
        searchList.setLayoutManager(new LinearLayoutManager(SearchActivity.this));

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchInput = inputText.getText().toString();
                onStart();
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Products");

        FirebaseRecyclerOptions<Clothes> options =
                new FirebaseRecyclerOptions.Builder<Clothes>()
                .setQuery(reference.orderByChild("name").startAt(searchInput),Clothes.class)
                .build();

        FirebaseRecyclerAdapter<Clothes, ClothesView> adapter =
                new FirebaseRecyclerAdapter<Clothes, ClothesView>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull ClothesView holder, int position, @NonNull final Clothes model) {

                        holder.txtClothesName.setText(model.getName());
                        holder.txtClothesDesc.setText(model.getDescription());
                        holder.txtClothesPrice.setText("Price: Rs."+model.getPrice());
                        Picasso.get().load(model.getImage()).into(holder.ClothImag);

                        holder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view)
                            {
                                Intent intent = new Intent(SearchActivity.this, clothes_profile.class);
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
        searchList.setAdapter(adapter);
        adapter.startListening();
    }
}