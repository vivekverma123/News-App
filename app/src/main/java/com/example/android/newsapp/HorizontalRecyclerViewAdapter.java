package com.example.android.newsapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class HorizontalRecyclerViewAdapter extends RecyclerView.Adapter<HorizontalRecyclerViewAdapter.HorizontalRVViewHolder>
{
    Context context;
    ArrayList<HorizontalModel> arrayList;

    public HorizontalRecyclerViewAdapter(Context context, ArrayList<HorizontalModel> arrayList)
    {
        this.context = context;
        this.arrayList = arrayList;
    }
    @NonNull
    @Override
    public HorizontalRVViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_horizontal,parent,false);
        return new HorizontalRVViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull HorizontalRVViewHolder holder, int position)
    {
        HorizontalModel horizontalModel=arrayList.get(position);
        String s=horizontalModel.getImageUrl();
        if(s.isEmpty())
            s="http://unbxd.com/blog/wp-content/uploads/2014/02/No-results-found.jpg";
        Picasso.get().load(s).into(holder.imageView);
        holder.textView.setText(horizontalModel.getHead());
        holder.t2.setText(horizontalModel.getDesc());
    }
    @Override
    public int getItemCount()
    {
        return arrayList.size();
    }
    public class HorizontalRVViewHolder extends RecyclerView.ViewHolder
    {
        TextView textView,t2;
        ImageView imageView;
        public HorizontalRVViewHolder(View itemView)
        {
            super(itemView);
            imageView=itemView.findViewById(R.id.image);
            textView=(TextView)itemView.findViewById(R.id.txttitleHorizontal);
            t2=(TextView)itemView.findViewById(R.id.txtdescHorizontal);
        }
    }
}
