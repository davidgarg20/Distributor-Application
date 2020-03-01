package com.example.distributorapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import android.os.Bundle;

public class PaymentMain extends AppCompatActivity {

    DatabaseHelper db;
    LinearLayout paymentmain;
    String selectedshop,s2;
    String lastorderno;
    Integer l;
    Cursor c1;
    Cursor p;
    String[] p1,p2;
    Button colpaybutton;
    TextView[] t1,t2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_main);

        Intent I = getIntent();
        selectedshop = I.getStringExtra("selectedshop");
        paymentmain = findViewById(R.id.paymentm);
        colpaybutton = findViewById(R.id.colpaybutton);

        p1= new String[10000];
        p2= new String[10000];

        t1= new TextView[10000];
        t2= new TextView[10000];

        db = new DatabaseHelper(this);

        s2 = selectedshop.replaceAll(" ","_");

        c1 = db.lastorderno(s2);
        c1.moveToLast();
        lastorderno = c1.getString(0);
        l = Integer.valueOf(lastorderno);

        p= db.payment(selectedshop);
        p.moveToLast();
        for( int i=1; i<p.getCount()+1 & i<10000;i++)
        {
            p1[i]=p.getString(1);
            p2[i]=p.getString(0);
            p.moveToPrevious();

        }




        for(int i=1; i<p.getCount()+1; i++){

            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View rowView = inflater.inflate(R.layout.paymentlayout,null);
            paymentmain.addView(rowView,paymentmain.getChildCount());
            t1[i]=rowView.findViewById(R.id.textView45);
            t2[i]=rowView.findViewById(R.id.textView46);

            t1[i].setText(p1[i]);
            t2[i].setText(p2[i]);

        }

        colpaybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PaymentMain.this,CollectPayment.class);
                i.putExtra("shopname",selectedshop);
                i.putExtra("modshopname",s2);
                startActivity(i);
            }
        });

    }
}
