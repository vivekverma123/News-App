package com.example.android.newsapp;

public class HorizontalModel
{
    String head,desc,imageUrl;

    public HorizontalModel(String head,String desc,String imageUrl)
    {
        this.imageUrl=imageUrl;
        this.desc = desc;
        this.head=head;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getDesc() {
        return desc;
    }

    public String getHead() {
        return head;
    }
}
