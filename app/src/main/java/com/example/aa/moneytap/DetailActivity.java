package com.example.aa.moneytap;

import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    RecyclerView recycler_view;
    RecyclerView.Adapter rAdapter;
    RecyclerView.LayoutManager layoutManager;
    ProgressDialog progressDialog;
    WebView webView;
    TextView tv_description;

    List<Item> itemDetails;


    RequestQueue rq;
    String request_url = "http://localhost/testapi.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        rq = Volley.newRequestQueue(this);
        recycler_view = (RecyclerView)findViewById(R.id.recycler_view);
        webView = (WebView)findViewById(R.id.webView);
        tv_description = (TextView)findViewById(R.id.tv_description);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recycler_view.setLayoutManager(layoutManager);
        itemDetails = new ArrayList<>();


        Bundle b = getIntent().getExtras();
        String name ;

        if (b != null) {
            name = b.getString("itemname");
            System.out.println(name);

        }

        getDetails();


    }

    public void getDetails(){

        String sl = "https://en.wikipedia.org//w/api.php?action=query&format=json&prop=pageimages%7Cpageterms&generator=prefixsearch&redirects=1&formatversion=2&piprop=thumbnail&pithumbsize=50&pilimit=10&wbptterms=description&gpssearch=Sachin+T&gpslimit=10";
        System.out.println(sl);
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("ProgressDialog");
        progressDialog.setMessage("Loading...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(false);
        progressDialog.show();



        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, sl, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            JSONObject jObj = new JSONObject(response.toString());
                            System.out.println("jobj :" + jObj.toString());
                            final JSONObject jobj = jObj.optJSONObject("query");
                            final JSONArray jarr = jobj.getJSONArray("pages");
                            progressDialog.dismiss();
                            for (int i = 0; i < jarr.length(); i++) {
                                JSONObject jObject = jarr.getJSONObject(i);
                                Item idata = new Item();
                                idata.setTitle(jObject.getString("title"));

                                try {
                                    idata.setImage(String.valueOf(jObject.getJSONObject("thumbnail").getString("source")));
                                }catch (JSONException e){
                                    e.printStackTrace();
                                }
                                try {

                                    JSONArray jarray = jObject.getJSONObject("terms").getJSONArray("description");

                                        String m = jarray.getString(0);
                                        idata.setDescription(jarray.getString(0));
                                        System.out.println(m);

                                }catch (JSONException e){
                                    e.printStackTrace();


                                }

                                itemDetails.add(idata);
                            }
                            rAdapter = new ItemAdapter(DetailActivity.this, itemDetails);
                            recycler_view.setAdapter(rAdapter);

                            recycler_view.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(),recycler_view, new RecyclerTouchListener.ClickListener() {
                                @Override
                                public void onClick(View view, int position) {
                                    JSONObject job;
                                    try {
                                        job = jarr.getJSONObject(position);
                                        String id  = job != null ? job.getString("pageid") : null;
                                        webView.getSettings().getJavaScriptEnabled();
                                        webView.loadUrl("http://en.wikipedia.org/?curid="+id);

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }



                                }

                                @Override
                                public void onLongClick(View view, int position) {

                                }
                            }));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("Error Occur" + error);

                    }
                }

        );

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjectRequest);


    }

}
