package com.example.distributorapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Searchview extends BaseAdapter {


    Context mContext;
    LayoutInflater inflater;
    private List<SearchQuery> searchQueries = null;
    private ArrayList<SearchQuery> arraylist;

    public Searchview(Context context, List<SearchQuery> searchQueries) {
        mContext = context;
        this.searchQueries = searchQueries;
        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<SearchQuery>();
        this.arraylist.addAll(searchQueries);
    }

    public class ViewHolder {
        TextView name;
    }

    @Override
    public int getCount() {
        return searchQueries.size();
    }

    @Override
    public String getItem(int position) {
        return searchQueries.get(position).getQuery();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.mylist, null);
            // Locate the TextViews in listview_item.xml
            holder.name = (TextView) view.findViewById(R.id.textView);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Set the results into TextViews
        holder.name.setText(searchQueries.get(position).getQuery());
        return view;
    }

    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        searchQueries.clear();
        if (charText.length() == 0) {
            searchQueries.addAll(arraylist);
        } else {
            for (SearchQuery wp : arraylist) {
                if (wp.getQuery().toLowerCase(Locale.getDefault()).contains(charText)) {
                    searchQueries.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }

}