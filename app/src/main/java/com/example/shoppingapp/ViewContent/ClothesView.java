package com.example.shoppingapp.ViewContent;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shoppingapp.Interface.ItemClickListner;
import com.example.shoppingapp.R;

public class ClothesView extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView txtClothesName, txtClothesDesc, txtClothesPrice;
    public ImageView ClothImag;
    public ItemClickListner listner;

    public ClothesView(@NonNull View itemView) {
        super(itemView);

        txtClothesName = (TextView) itemView.findViewById(R.id.productName);
        txtClothesDesc = (TextView) itemView.findViewById(R.id.productDesc);
        txtClothesPrice = (TextView) itemView.findViewById(R.id.productPrice);
        ClothImag = (ImageView) itemView.findViewById(R.id.productImage);


    }

    public void setItemClickListner(ItemClickListner listner){
        this.listner = listner;
    }

    @Override
    public void onClick(View view) {
        listner.onClick(view,getAdapterPosition(), false);
    }
}
