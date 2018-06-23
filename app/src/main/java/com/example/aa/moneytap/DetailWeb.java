package com.example.aa.moneytap;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.Arrays;

public class DetailWeb extends AppCompatActivity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_web);

        webView = (WebView)findViewById(R.id.webView);
        Bundle bun = getIntent().getExtras();

        String s3 = bun !=null ? bun.getString("page") : null;

        webView.getSettings().getJavaScriptEnabled();
        webView.loadUrl("http://en.wikipedia.org/?curid="+s3);




    }
}
