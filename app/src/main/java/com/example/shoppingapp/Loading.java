package com.example.shoppingapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;

import com.example.shoppingapp.R;

public class Loading {

    private Activity activity;
    private AlertDialog dialog;


    Loading(Activity myactivity){
        activity=myactivity;
    }
    void PaymentLoadingAnimation(){
        AlertDialog.Builder builder=new AlertDialog.Builder(activity);

        LayoutInflater intflater = activity.getLayoutInflater();
        builder.setView(intflater.inflate(R.layout.activity_main2,null));
        builder.setCancelable(false);

        dialog=builder.create();
        dialog.show();

    }
    void dismissDialog(){
        dialog.dismiss();

    }

}
