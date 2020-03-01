package com.example.distributorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

public class Thankyouactivity extends AppCompatActivity {
Button backmain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thankyouactivity);
        backmain = findViewById(R.id.button13);

        backmain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Thankyouactivity.this, SalesmanMain.class);
                startActivity(i);
            }
        });
    }
}
