package com.example.distributorapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

import android.os.strictmode.SqliteObjectLeakedViolation;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Shop.db";
    public static final String TABLE_NAME = "Shop_TABLE";
    public static final String COL_0 = "ID";
    public static final String COL_1 = "Shop_Name";
    public static final String COL_2 = "Owners_Name";
    public static final String COL_3 = "Contact_No";
    public static final String COL_4 = "Address";
    public static final String QTY = "Qty";
    public static final String AMOUNT = "Amount";
    public static final String PRICE = "Price";
    public static final String ORDERNO = "Orderno";
    public static final String ITEMNAME = "Itemname";
    public static final String ORDERDATE = "Orderdate";




    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);

    }
 SQLiteDatabase db1 = this.getReadableDatabase();


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table Shop_TABLE (ID INTEGER PRIMARY KEY AUTOINCREMENT , Shop_Name Text, Owners_name TEXT, Contact_No INTEGER, Address TEXT )");
        Log.d("SQLMessage", "TABLE WAS CREATED ");

    }


    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Shop_TABLE");
        onCreate(db);
    }

    public boolean insertData(String id , String shopname, String ownername, String contactno, String address) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_0, id);
        values.put(COL_1, shopname);
        values.put(COL_2, ownername);
        values.put(COL_3, contactno);
        values.put(COL_4, address);

        long result = db.insert(TABLE_NAME, null, values);
        if (result == -1)
            return false;
        else
            return true;
    }


    public Cursor getData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM Shop_TABLE", null);
        return res;
    }

    public Cursor getbasicorder(String Tablename, Integer Orderno) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] columns = new String[] {  ORDERDATE };
        String[] selection= new String[] { Orderno.toString()};
        Cursor cursor = db.query(Tablename, columns, "Orderno=?", selection, null, null, null);

        return cursor;
    }

    public boolean updateData(String id, String shopname, String ownername, String contactno, String address) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_0, id);
        values.put(COL_1, shopname);
        values.put(COL_2, ownername);
        values.put(COL_3, contactno);
        values.put(COL_4, address);
        db.update(TABLE_NAME, values, "ID = ?", new String[]{id});
        return true;
    }
    public Cursor fetch() {
        Log.d("david","fetchcalled");
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = new String[] {  COL_1 };
        Cursor cursor = db.query(TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }
    public Integer deleteData(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?", new String[]{id});

    }

    public void Create(String Tablename) {
        Cursor cursor = db1.rawQuery("select DISTINCT tbl_name from sqlite_master where tbl_name = '"
                + Tablename + "'", null );
        if(cursor.getCount()==0)
        {String CREATE_TABLE_NEW_SHOP = "CREATE TABLE " + Tablename + " ( Orderno INTEGER , Itemname Text,  Qty INTEGER, Price INTEGER, Amount Integer, Orderdate DATE)";
        db1.execSQL(CREATE_TABLE_NEW_SHOP);
        String CREATE_TABLE = "CREATE TABLE " + Tablename + "del" + "( Orderno INTEGER , Amount INTEGER , DelStatus String , DelTime DATE )";
        db1.execSQL(CREATE_TABLE);
            String CREATE_TABLE1 = "CREATE TABLE " + Tablename + "payment" + "( PayAmount INTEGER , PayDate DATE )";
            db1.execSQL(CREATE_TABLE1);
        Log.d("SQLMessage", "TABLE 2 WAS CREATED ");
        }

    }


    public boolean insertdata3( String Tablename, Integer Orderno, Integer Amount )
    {  SQLiteDatabase db = this.getWritableDatabase();
    ContentValues values = new ContentValues();
    String table = Tablename + "del";
    String Delstatus = "Pending";
    values.put( "OrderNo" , Orderno );
    values.put( "Amount", Amount);
    values.put( "DelStatus", Delstatus);
    db.insert(table,null,values);
        return true;
    }

    public boolean delstatusupdate ( String Tablename , Integer Orderno){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        String table = Tablename + "del";
        values.put("Ordeno", Orderno);
        values.put("DelStatus", "Delivered");
        db.insert(table,null,values);
        return true;

    }

    public boolean insertdata4( String Tablename, Integer Amount , String Date )
    {  SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        String table = Tablename + "payment";
        values.put( "PayAmount", Amount);
        values.put( "PayDate", Date);
        db.insert(table,null,values);
        return true;
    }

    public  Cursor payment ( String Tablename){
        SQLiteDatabase db = this.getWritableDatabase();
        String table = Tablename + "payment";
        String[] columns = { "PayAmount" , "PayDate"};
        Cursor c = db.query( table, columns ,null,null,null,null,null);
        return c;



    }

    public boolean insertData1(String Tablename, Integer orderno , String itemname, Integer qty, Integer Price , Integer Amount , String Date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ORDERNO, orderno);
        values.put(ITEMNAME, itemname);
        values.put(QTY, qty);
        values.put(PRICE, Price);
        values.put( AMOUNT , Amount);
        values.put( ORDERDATE , Date);
        Log.d("sql","data inserted in table 2");


        long result = db.insert(Tablename, null, values);
        if (result == -1)
            return false;
        else
            return true;
    }

    public Cursor getorder(String Tablename ,  String orderno)
    {
        SQLiteDatabase db= this.getWritableDatabase();
        Log.d("sql","getorder executed");
        String[] columns = new String[] { ITEMNAME,QTY,PRICE};
        String[] args = new String[] { orderno};
        String selectQuery = "SELECT  Itemname, Qty , Price  FROM " + Tablename + " WHERE Orderno=" + orderno;
        Cursor cursor = db.query(Tablename, columns, "Orderno=?", args, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }
    public Cursor lastorderno(String Tablename) {
      SQLiteDatabase db= this.getWritableDatabase();
        String[] columns = new String[] { ORDERNO };
        Log.d("dsfdsf","lastorderno executed");
        Cursor cursor = db.query(Tablename, columns, null, null, null, null, null);

        return cursor;
    }
}
