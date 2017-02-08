package com.zappos.ilovezappos;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.zappos.ilovezappos.databinding.ProductViewBinding;


/**
 * Created by Dhruvil on 29-01-2017.
 */

public class ProductDetail extends AppCompatActivity implements View.OnClickListener{
    String productUrl;
    ImageView backArrow,share,cart;
    TextView headerTitle,webLink,orignalPrice,percentOff,count;
    FloatingActionButton cartFloat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ProductViewBinding binding = DataBindingUtil.setContentView(this,R.layout.product_view);
        Intent i = getIntent();
        cart = (ImageView) findViewById(R.id.cart);
        count = (TextView) findViewById(R.id.count);
        headerTitle = (TextView)findViewById(R.id.header_name);
        backArrow = (ImageView)findViewById(R.id.header_backarrow);
        webLink = (TextView)findViewById(R.id.webpage_link);
        share = (ImageView) findViewById(R.id.share);
        orignalPrice = (TextView) findViewById(R.id.orignal_price);
        percentOff = (TextView) findViewById(R.id.percent_off);
        cartFloat = (FloatingActionButton) findViewById(R.id.cart_float);

        share.setVisibility(View.VISIBLE);
        cart.setVisibility(View.VISIBLE);
        backArrow.setOnClickListener(this);
        cartFloat.setOnClickListener(this);
        share.setOnClickListener(this);
        webLink.setOnClickListener(this);

        headerTitle.setText(i.getStringExtra("brandName"));
        webLink.setPaintFlags(webLink.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);
        productUrl =  i.getStringExtra("productUrl");

        ProductInfo productInfo = new ProductInfo(i.getStringExtra("thumbnailImageUrl"),i.getStringExtra("brandName"),
                i.getStringExtra("originalPrice"), i.getStringExtra("price"),i.getStringExtra("percentOff") + " OFF",
                i.getStringExtra("productName"),i.getStringExtra("type"),false,i.getStringExtra("productUrl"));


        if (!i.getStringExtra("thumbnailImageUrl").equals("")) {
            Picasso.with(binding.imageView2.getContext()).load(i.getStringExtra("thumbnailImageUrl")).resize(200, 200).into(binding.imageView2);
        }

        if(!i.getStringExtra("percentOff").equals("0%")){
            Log.i("percent","Zero");
            orignalPrice.setVisibility(View.VISIBLE);
            percentOff.setVisibility(View.VISIBLE);

        }

        binding.setInfo(productInfo);

    }

    @Override
    public void onClick(View v) {
        if(v==backArrow){
            onBackPressed();
            overridePendingTransition(
                    R.anim.slide_in_left,
                    R.anim.slide_out_right);
        }
        else if( v == share){
            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            String shareBody = productUrl;
            sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Zappos");
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
            startActivity(Intent.createChooser(sharingIntent, "Share via"));
        }
        else if( v == webLink){
            Intent i = new Intent(getApplicationContext(), ProductWebView.class);
            i.putExtra("Url", productUrl);
            Log.i("Url Passing",productUrl);
            startActivity(i);
            overridePendingTransition(
                    R.anim.slide_in_right,
                    R.anim.slide_out_left);
        }
        else if( v == cartFloat){
            ObjectAnimator animation = ObjectAnimator.ofFloat(cartFloat, "rotationY", 0.0f, 360f);
            animation.setDuration(1200);
            animation.setRepeatCount(0);
            animation.setInterpolator(new AccelerateDecelerateInterpolator());
            animation.start();
            ObjectAnimator animation1 = ObjectAnimator.ofFloat(cart, "rotationY", 0.0f, 360f);
            animation.setDuration(1200);
            animation.setRepeatCount(0);
            animation.setInterpolator(new AccelerateDecelerateInterpolator());
            animation1.start();
            count.setVisibility(View.VISIBLE);
            count.setText("1");
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

