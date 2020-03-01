package com.example.distributorapp;

public class SearchQuery {

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    private String query;
    public SearchQuery(String query)
    {
        this.query = query;
    }
}