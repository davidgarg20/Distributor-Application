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



public class PreviousOrder extends AppCompatActivity {
    DatabaseHelper mydb;
    Cursor c1;
    Cursor c;
    String lastorderno;
    String[] ss;
    String[] date;
    LinearLayout Layout;
    TextView[] dateview;
    TextView[] setorederno;
    TextView shopname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previous_order);

        mydb = new DatabaseHelper(this);

        Layout = findViewById(R.id.Layout);
        setorederno = new TextView[1000];
        dateview = new TextView[1000];
        date = new String[1000];
        ss = new String[1000];
        shopname=findViewById(R.id.prevordershopname);
        Intent I = getIntent();
         final String selectedshop = I.getStringExtra("selectedshop");

         shopname.setText(selectedshop);

        c = mydb.lastorderno(selectedshop);

        if(c.getCount()==0) {
            lastorderno = "0";
        }
        else
        {   c.moveToLast();
            lastorderno=c.getString(0);
        }

        int l= Integer.valueOf(lastorderno);


        //int l = Integer.valueOf(lastorderno);
        Log.d( "hbh","ggghjgjghjhyg");


        for(int i=1; i<l+1;i++){
            c1 = mydb.getbasicorder(selectedshop,i);
            {   c1.moveToLast();
                date[i]=c1.getString(0);
            }
            ss[i]= Integer.toString(i) ;
            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final View rowView = inflater.inflate(R.layout.layout3, null);
            Layout.addView( rowView, Layout.getChildCount() );
            dateview[i]=rowView.findViewById(R.id.lay3date);
            setorederno[i]=rowView.findViewById(R.id.lay3orderno);
            setorederno[i].setText(ss[i]);
            dateview[i].setText(date[i]);
        }
    }
}





