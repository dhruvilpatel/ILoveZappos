package com.zappos.ilovezappos;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Dhruvil on 31-01-2017.
 */

public class ProductWebView extends AppCompatActivity implements View.OnClickListener{

    private WebView mWebview ;
    ImageView backArrow;
    String url;
    TextView headerTitle,browser;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_view);

        headerTitle = (TextView)findViewById(R.id.header_name);
        mWebview = (WebView)findViewById(R.id.webview);
        backArrow = (ImageView)findViewById(R.id.header_backarrow);
        browser = (TextView)findViewById(R.id.browser);

        browser.setVisibility(View.VISIBLE);
        backArrow.setOnClickListener(this);
        browser.setOnClickListener(this);

        Intent i = getIntent();
        url =  i.getStringExtra("Url");
        headerTitle.setText("Zappos");

        mWebview.getSettings().setJavaScriptEnabled(true);
        mWebview.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(getApplicationContext(), description, Toast.LENGTH_SHORT).show();
            }
        });

        mWebview.loadUrl(url);

    }
    @Override
    public void onClick(View v) {
        if(v == browser){
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(browserIntent);
        }
        else if(v == backArrow){
            onBackPressed();
            overridePendingTransition(
                    R.anim.slide_in_left,
                    R.anim.slide_out_right);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(
                R.anim.slide_in_left,
                R.anim.slide_out_right);
    }


}

