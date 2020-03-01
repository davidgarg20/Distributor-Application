package com.example.distributorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.widget.LinearLayout;
import android.view.View;
public class SalesmanMain extends AppCompatActivity {
    public LinearLayout neword,prevord,payment,delivery;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salesman_main);
        neword = findViewById(R.id.neworder);
        prevord = findViewById(R.id.prevord);
        payment = findViewById(R.id.payment);
        delivery = findViewById(R.id.delivery);

        neword.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2 = new Intent(SalesmanMain.this, Order.class);
                startActivity(i2);
            }
        } ) ;


    prevord.setOnClickListener( new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i2 = new Intent(SalesmanMain.this, SelectShop2.class);
            startActivity(i2);
        }
    } ) ;

    payment.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i2 = new Intent(SalesmanMain.this, SelectShop.class);
            startActivity(i2);

        }
    });

        delivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2 = new Intent(SalesmanMain.this, SelectShop2.class);
                startActivity(i2);

            }
        });


    };
};


