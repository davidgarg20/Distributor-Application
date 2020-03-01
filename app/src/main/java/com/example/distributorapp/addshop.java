package com.example.distributorapp;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class addshop extends AppCompatActivity {
    DatabaseHelper mydb;
    EditText ID , Shop_Name, Owners_Name,Contact_No,Address;
    Button btnAdd, btnView , btnDel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addshop);

            ID = findViewById(R.id.editText2);
            Shop_Name = findViewById(R.id.editText3);
            Owners_Name = findViewById(R.id.editText4);
            Contact_No = findViewById(R.id.editText5);
            Address = findViewById(R.id.editText6);
            btnAdd = findViewById(R.id.button11);

            mydb  = new DatabaseHelper(this);

            addData();
            //deleteData();

            // updateData();
        }

        private void addData() {
            btnAdd.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View view) {
                                              boolean isInserted = mydb.insertData(ID.getText().toString(), Shop_Name.getText().toString(), Owners_Name.getText().toString(), Contact_No.getText().toString(), Address.getText().toString());
                                              String a=Boolean.toString(isInserted);
                                              Log.i("Output123",a);

                                              if (isInserted == true)
                                                  Toast.makeText(addshop.this, "Data Inserted", Toast.LENGTH_LONG).show();
                                              else
                                                  Toast.makeText(addshop.this, "Data not Inserted", Toast.LENGTH_LONG).show();

                                              Intent i2 = new Intent(addshop.this, Order.class);
                                              startActivity(i2);
                                          }


                                      }
            );
        }

    /*private void deleteData() {
        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer delRow = mydb.deleteData(ID.getText().toString());

        };
    });
    }/*


    private void viewData() {
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = mydb.getData();
                if(res.getCount()==0){
                  //  showMessage("Error","No Data Found");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("ID :"+ res.getString(0)+"\n");
                    buffer.append("First Name :"+ res.getString(1)+"\n");
                    buffer.append("last Name :"+ res.getString(2)+"\n");
                    buffer.append("Marks :"+ res.getString(3)+"\n\n");
                }
            }
        });
    }

   /* public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }*/


   /* private void updateData() {
        boolean isUpdate = mydb.updateData(ID.getText().toString(), Shop_Name.getText().toString(), Owners_Name.getText().toString(),Contact_No.getText().toString(),Address.getText().toString());

        if(isUpdate)
            Toast.makeText(ordermain.this,"Database Updated", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(ordermain.this,"Update Failed", Toast.LENGTH_SHORT).show();
    }*/
    }


