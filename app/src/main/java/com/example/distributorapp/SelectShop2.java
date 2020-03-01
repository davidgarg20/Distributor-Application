package com.example.distributorapp;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.database.Cursor;
import android.widget.SearchView;


import java.util.ArrayList;

public class SelectShop2 extends AppCompatActivity {

    SearchView ShopSearch;

    private DatabaseHelper db;
    private ListView listView;
    private TextView tv;
    private ArrayAdapter adapter;
    private ArrayList<String> listItem;
    ArrayList<SearchQuery> arraylist = new ArrayList<SearchQuery>();
    Searchview Adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_shop2);

        db = new DatabaseHelper(this);
        Cursor cursor = db.fetch();

        listView = findViewById(R.id.shoplist);
        tv= findViewById(R.id.tv);

        listItem = new ArrayList<>();
        if (cursor.getCount() == 0 ) {
            Toast.makeText(this, "No data to Show",Toast.LENGTH_SHORT).show();
        }
        else
            while ( cursor.moveToNext()){
                listItem.add(cursor.getString(0));
            }
        for (String searchQuery:listItem) {
            SearchQuery searchQuery1 = new SearchQuery(searchQuery);
            // Binds all strings into an array
            arraylist.add(searchQuery1);
        }


        Adapter = new Searchview(this, arraylist);
        Adapter.notifyDataSetChanged();


        ShopSearch = findViewById(R.id.searchView1);

        CharSequence query = ShopSearch.getQuery();

        ShopSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                String text = newText;
                Adapter.filter(text);
                return false;
            }
        });

        listView.setAdapter(Adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent i4 = new Intent(SelectShop2.this,PreviousOrder.class);
                String s1 = Adapter.getItem(position).toString();
                i4.putExtra( "selectedshop", s1);
                startActivity(i4);

            }
        });





    }


};