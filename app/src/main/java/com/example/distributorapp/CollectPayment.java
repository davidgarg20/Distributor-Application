package com.example.distributorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;
import android.content.Intent;


import android.os.Bundle;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CollectPayment extends AppCompatActivity {

    TextView Shopname;
    EditText Amount;
    Button Paymentrec;
    String shopname,modshopname;
    Integer amount;
    DatabaseHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect_payment);
        Shopname = findViewById(R.id.collectpayshop);
        Amount = findViewById(R.id.collectpayamount);
        Paymentrec = findViewById(R.id.collectpayrecbutton);

        mydb = new DatabaseHelper(this);

        Intent i = getIntent();
        shopname = i.getStringExtra("shopname");
        modshopname = i.getStringExtra("modshopname");

        Shopname.setText(shopname);


        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-YYYY hh:mm:ss");
        final String fDate = df.format(c);

        Paymentrec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amount= Integer.valueOf(Amount.getText().toString());
                mydb.insertdata4(modshopname,amount,fDate);
                Intent i = new Intent(CollectPayment.this, SalesmanMain.class);
                startActivity(i);

            }
        });

    }
}
