package com.example.distributorapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationManager;
import android.content.ClipData;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.view.View;
import android.widget.ScrollView;
import android.view.LayoutInflater;
import android.content.Context;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import android.widget.EditText;
import androidx.core.app.NotificationCompat;


public class PlaceOrder extends AppCompatActivity {
    DatabaseHelper mydb;
    TemporaryDatabase tempdb;
    TextView txt;
    String[] itemlist;
    String[] itemplist;
    String[] itemqlist;
    String[] itemalist;
    Integer[] ITEMALIST;
    TextView[] Itemlist;
    TextView[] Itemplist;
    TextView[] Itemqlist;
    TextView[] Itemalist;
    Integer[] ITEMQLIST;
    Integer[] ITEMPLIST;
    Cursor cursor;
    int count = 0;
    LinearLayout linearlayout;
    Integer Amount=0;
    TextView amt;
    String amount;
    Button placeorder;

    private NotificationManagerCompat Notificationmanager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_order);
        amt = findViewById(R.id.TextView);

        placeorder = findViewById(R.id.button16);

        Notificationmanager =  NotificationManagerCompat.from(this);


        Intent i = getIntent();
        final String selectedshop = i.getStringExtra("selectedshop");
        final Integer orderno = i.getIntExtra("orderno", 0);
        final String Orderno = orderno.toString();

        final String s2 = selectedshop.replaceAll(" ","_" );
        mydb = new DatabaseHelper(this);
        tempdb = new TemporaryDatabase(this);
        cursor = tempdb.gettemporder( Orderno);
        count = cursor.getCount();
        cursor.moveToPosition(0);

        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-YYYY hh:mm:ss");
        final String fDate = df.format(c);


        ITEMALIST = new Integer[1000];
        ITEMPLIST = new Integer[1000];
        ITEMQLIST = new Integer[1000];


        itemlist = new String[1000];
        itemplist = new String[1000];
        itemqlist = new String[1000];
        itemalist = new String[1000];

        Itemlist = new TextView[1000];
        Itemplist = new TextView[1000];
        Itemqlist = new TextView[1000];
        Itemalist = new TextView[1000];

        linearlayout = findViewById(R.id.linearlayt);

        for( int p =0; p<count; p++) {
            Integer q = cursor.getPosition();
            Log.d("sql","loop executed");
            itemlist[p] = cursor.getString(0);
            itemqlist[p] = cursor.getString(1);
            itemplist[p] = cursor.getString(2);
            ITEMPLIST[p] = Integer.valueOf(itemplist[p]);
            ITEMQLIST[p] =       Integer.valueOf(itemqlist[p]);
            ITEMALIST[p] = ITEMQLIST[p] * ITEMPLIST[p];
            itemalist[p] = ITEMALIST[p].toString();
            cursor.moveToNext();
            Amount = Amount + ITEMALIST[p];

        }
        amount = Amount.toString();
        amt.setText(amount);
        for (int q = 0; q < count; q++) {
            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final View rowView = inflater.inflate(R.layout.layout2, null);
            linearlayout.addView( rowView, linearlayout.getChildCount() );
            Itemlist[q] = rowView.findViewById(R.id.textView15);
            Itemqlist[q] = rowView.findViewById(R.id.textView17);
            Itemplist[q] = rowView.findViewById(R.id.textView19);
            Itemalist[q] = rowView.findViewById(R.id.textView27);
        }

        for (int s=0; s< count; s++){
            Log.d("sql","loop2executed");
            Itemlist[s].setText(itemlist[s]);
            Itemqlist[s].setText(itemqlist[s]);
            Itemplist[s].setText(itemplist[s]);
            Itemalist[s].setText(itemalist[s]);
        }

       placeorder.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent I = new Intent(PlaceOrder.this,Thankyouactivity.class);
               sendonordernotification();

               for (int p=0; p<count;p++){
                   mydb.insertData1(s2, orderno, itemlist[p] , ITEMQLIST[p] ,ITEMPLIST[p] , ITEMALIST[p],fDate);

                   tempdb.cleardata();

                   mydb.insertdata3( s2,orderno,Amount);

               startActivity(I);

              }

           }
       });
    }
 public void  sendonordernotification() {
        Log.e("Notification error", "chala");

     NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "OrderNotification")
             .setSmallIcon(R.drawable.ic_notify)
             .setContentTitle("Distributor App")
             .setContentText("New Order Created")
             .setStyle(new NotificationCompat.BigTextStyle())
             .setPriority(NotificationCompat.PRIORITY_HIGH);


             Notificationmanager.notify( 1 , builder.build());


 }
}
