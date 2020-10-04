package com.example.shoppingapp.ViewContent;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shoppingapp.Interface.ItemClickListner;
import com.example.shoppingapp.R;

public class CartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    public TextView txtProductName,txtProductPrice, txtProductQuantity;
    private ItemClickListner itemClickListner;
    public ImageView clothimagecart;

    public CartViewHolder(@NonNull View itemView)
    {
        super(itemView);

        txtProductName = itemView.findViewById(R.id.cart_product_name);
        txtProductQuantity = itemView.findViewById(R.id.cart_product_quantity);
        txtProductPrice = itemView.findViewById(R.id.cart_product_price);


    }


    @Override
    public void onClick(View view)
    {

        itemClickListner.onClick(view, getAdapterPosition(), false);
    }

    public void setItemClickListner(ItemClickListner itemClickListner)
    {
        this.itemClickListner = itemClickListner;
    }
}
