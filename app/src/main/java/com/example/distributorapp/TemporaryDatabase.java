package com.example.distributorapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import android.util.Log;

public class TemporaryDatabase  extends SQLiteOpenHelper  {
    public static final String DATABASE_NAME = "temp.db";
    public static final String TABLE_NAME = "Shop";
    public static final String QTY = "Qty";
    public static final String DELDATE = "Deldate";
    public static final String PRICE = "Price";
    public static final String ORDERNO = "Orderno";
    public static final String ITEMNAME = "Itemname";
    public static final String ORDERDATE = "Orderdate";
    public static Context context;



    public TemporaryDatabase(@Nullable Context context) {
            super(context, DATABASE_NAME, null, 1);

        }

    //private static String FIRST_DB_PATH = context.getDatabasePath("shop.db").toString();

        @Override
        public void onCreate(SQLiteDatabase db) {

            String CREATE_TABLE_NEW_SHOP = "CREATE TABLE " + TABLE_NAME + " ( Orderno INTEGER , Itemname Text,  Qty INTEGER, Price INTEGER";
            db.execSQL(CREATE_TABLE_NEW_SHOP);
            Log.d("SQLMessage", "TABLE WAS CREATED ");

        }
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS Shop_TABLE");
            onCreate(db);
        }

    public boolean insertData( Integer orderno , String itemname, Integer qty, Integer Price ) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ORDERNO, orderno);
        values.put(ITEMNAME, itemname);
        values.put(QTY, qty);
        values.put(PRICE, Price);
        Log.d("sql","data inserted in table 2");


        long result = db.insert(TABLE_NAME, null, values);
        if (result == -1)
            return false;
        else
            return true;
    }
    public Cursor gettemporder(  String orderno)
    {
        SQLiteDatabase db= this.getWritableDatabase();
        Log.d("sql","getorder executed");
        String[] columns = new String[] { ITEMNAME,QTY,PRICE};
        String[] args = new String[] { orderno};
        String selectQuery = "SELECT  Itemname, Qty , Price  FROM " + TABLE_NAME + " WHERE Orderno=" + orderno;
        Cursor cursor = db.query(TABLE_NAME, columns, "Orderno=?", args, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public boolean cleardata(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+ TABLE_NAME);
        return true;
    }
}
