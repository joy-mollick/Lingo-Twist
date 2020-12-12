package com.example.mbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

public class Facebook extends AppCompatActivity {
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facebook);
        Intent intent = getIntent();
        String str = intent.getStringExtra("message_key");
        webView = (WebView) findViewById(R.id.web_view);

        if (savedInstanceState != null) {
            webView.restoreState(savedInstanceState);
        } else {
            webView.getSettings().setJavaScriptEnabled(true);
            webView.getSettings().setUseWideViewPort(true);
            webView.getSettings().setLoadWithOverviewMode(true);
            webView.getSettings().setSupportZoom(true);
            webView.getSettings().setSupportMultipleWindows(true);
            webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
            webView.setBackgroundColor(Color.WHITE);
        }
        webView.setWebViewClient(new MyWebViewClient());
        webView.loadUrl(str);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (webView.canGoBack()) {
            webView.goBack();
        }
        else
        {
            Intent i=new Intent(Facebook.this,Contact_of_friends.class);
            startActivity(i);
            finish();
        }
    }
}