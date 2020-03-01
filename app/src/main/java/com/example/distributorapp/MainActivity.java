package com.example.distributorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;

import android.view.View;
import android.widget.Button;

import com.example.distributorapp.ui.login.Distributor_LOGIN;
import com.example.distributorapp.ui.login.ShopLoginActivity;

public class MainActivity extends AppCompatActivity {
 Button dist,salesman,shop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dist= findViewById(R.id.button);
        salesman= findViewById(R.id.button2);
        shop= findViewById(R.id.button3);


        dist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Distributor_LOGIN.class);
                startActivity(i);
            }
        } ) ;


        salesman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent i2 = new Intent(MainActivity.this, SalesmanMain.class);

            startActivity(i2);}
        } );


                shop.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    Intent i3 = new Intent(MainActivity.this, ShopLoginActivity.class);

                    startActivity(i3);}
                }  );




    };
};
