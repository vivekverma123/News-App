package com.example.android.newsapp;

import java.util.ArrayList;

public class VerticalModel
{
    String title;
    ArrayList<HorizontalModel> arrayList;

    public String getTitle() {
        return title;
    }

    public ArrayList<HorizontalModel> getArrayList() {
        return arrayList;
    }

    public VerticalModel(String title, ArrayList<HorizontalModel> arrayList) {
        this.title = title;
        this.arrayList = arrayList;
    }
}