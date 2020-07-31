package com.example.android.newsapp;

import android.app.ProgressDialog;
import android.support.v4.widget.CircularProgressDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView verticalRecyclerView;
    ArrayList<VerticalModel> arrayList = new ArrayList<>();
    VerticalRecyclerViewAdapter adapter = new VerticalRecyclerViewAdapter(this, arrayList);
    String url_bitcoins= "https://newsapi.org/v2/everything?q=bitcoin&from=2019-01-02&sortBy=publishedAt&apiKey=1a10cc57f4334f4a9d86324d042e5a26";
    String url_business="https://newsapi.org/v2/top-headlines?country=us&category=business&apiKey=1a10cc57f4334f4a9d86324d042e5a26";
    String url_apple="https://newsapi.org/v2/everything?q=apple&from=2019-02-01&to=2019-02-01&sortBy=popularity&apiKey=1a10cc57f4334f4a9d86324d042e5a26";
    String url_techCrunch="https://newsapi.org/v2/top-headlines?sources=techcrunch&apiKey=1a10cc57f4334f4a9d86324d042e5a26";
    String url_wallStreet="https://newsapi.org/v2/everything?domains=wsj.com&apiKey=1a10cc57f4334f4a9d86324d042e5a26";
    String url[]=new String[5];
    String title[]=new String[5];
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        url[0]=url_bitcoins;title[0]="Bitcoins";
        url[1]=url_business;title[1]="Business";
        url[2]=url_apple;title[2]="Apple";
        url[3]=url_techCrunch;title[3]="TechCrunch";
        url[4]=url_wallStreet;title[4]="WallStreet";
        verticalRecyclerView = findViewById(R.id.recyclerview);
        verticalRecyclerView.setHasFixedSize(true);
        verticalRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        setData();
    }

    private void setData() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("LOADING !");
        progressDialog.show();
        for (int k = 0; k < 5; k++) {
            final ArrayList<HorizontalModel> hr = new ArrayList<>();
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url[k], new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    progressDialog.dismiss();
                    Log.e("Line 55", "here");
                    try {
                        JSONObject root = new JSONObject(response);
                        JSONArray elem = root.getJSONArray("articles");
                        for (int i = 0; i < elem.length(); i++) {
                            JSONObject o = elem.getJSONObject(i);
                            hr.add(new HorizontalModel(o.getString("title"), o.getString("description"),o.getString("urlToImage")));
                        }
                        VerticalModel vr = new VerticalModel(title[arrayList.size()], hr);
                        arrayList.add(vr);
                        verticalRecyclerView.setAdapter(adapter);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    if (error != null && error.getMessage() != null)
                        Log.e("Line 77", error.getMessage());
                }
            });
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
        }
    }
}
