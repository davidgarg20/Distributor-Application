package com.example.distributorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.TextView;
import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.view.View;
import android.view.LayoutInflater;
import android.content.Context;
import android.widget.Button;
import android.widget.Toast;
import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import android.widget.EditText;

public class Ordermain extends AppCompatActivity {

    TextView Shopname;
    LinearLayout parentLinearLayout;
    Button additem,placeorder;
    DatabaseHelper mydb;
    TemporaryDatabase tempdb;
    SQLiteDatabase db;
    Cursor Lastorderno;
    String lastorderno;
    Integer alastorderno;
    Integer Orderno;
String[] itemname;
    Integer[] itemqty;
    Integer[] itemprice;
Integer count=0;
EditText[] item;
EditText[] qty;
EditText[] price;
EditText Item;
EditText Qty;
EditText Price;
String Itemname;
Integer Itemqty;
Integer Itemprice;
    String s2;


    TextView test;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordermain2);
        itemname = new String[1000];
        itemqty = new Integer[1000];
        itemprice = new Integer[1000];
        additem = findViewById(R.id.additem);

        Shopname = findViewById(R.id.shopname);

        Intent i5 = getIntent();

        final String selectedshop = i5.getStringExtra("selectedshop");

        s2 = selectedshop.replaceAll(" ","_");

        Shopname.setText(selectedshop);
        parentLinearLayout = (LinearLayout) findViewById(R.id.parentlinearlayout);

        additem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAddField(v);
            }
        });
        mydb  = new DatabaseHelper(this);
        tempdb = new TemporaryDatabase(this);
        createshop(s2);

        Lastorderno = mydb.lastorderno(s2);

        if(Lastorderno.getCount()==0) {
            lastorderno = "0";
        }
        else
        {   Lastorderno.moveToLast();
            lastorderno=Lastorderno.getString(0);
        }
        alastorderno = Integer.valueOf(lastorderno);
        Orderno=alastorderno+1;




        item = new EditText[1000];
        qty = new EditText[1000];
        price = new EditText[1000];

        Item = findViewById(R.id.Edittext22);
        Qty = findViewById(R.id.editText23);
        Price = findViewById(R.id.editText24);



        placeorder= findViewById(R.id.placeorder);
        placeorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Itemname = Item.getText().toString();
                Itemqty = Integer.valueOf(Qty.getText().toString());
                Itemprice = Integer.valueOf(Price.getText().toString());
                tempdb.insertData(  Orderno, Itemname, Itemqty , Itemprice );


                for(int i=0;i<count;i++) { itemname[i]= item[i].getText().toString();
                    itemqty[i]= Integer.valueOf(qty[i].getText().toString());
                    itemprice[i]= Integer.valueOf(price[i].getText().toString());

                    tempdb.insertData(  Orderno, itemname[i], itemqty[i] , itemprice[i]  );

                }

                Intent i = new Intent(Ordermain.this, PlaceOrder.class);
                i.putExtra("orderno", Orderno );
                i.putExtra("selectedshop", selectedshop );
                startActivity(i);
            }
        });

    }

    public void onAddField(View v) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.itemrow, null);
        parentLinearLayout.addView( rowView, parentLinearLayout.getChildCount() - 1);
        item[count] = rowView.findViewById(R.id.itemname);
        qty[count] = rowView.findViewById(R.id.quantity);
        price[count] = rowView.findViewById(R.id.price);
        count++;
        // Add the new row before the add field button. f

    }



    private void createshop( String shopname) {
        mydb.Create(shopname);
    }

}
