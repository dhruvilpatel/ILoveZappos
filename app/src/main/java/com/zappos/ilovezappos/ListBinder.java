package com.zappos.ilovezappos;

import android.content.Intent;
import android.databinding.BindingAdapter;
import android.databinding.ObservableArrayList;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


/**
 * Created by Dhruvil on 28-01-2017.
 */

public class ListBinder {

    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView imageView, String url) {
        if (!url.equals("")) {
            Picasso.with(imageView.getContext()).load(url).resize(200, 200).into(imageView);
        }
    }
    @BindingAdapter({"bind:isVisible"})
    public static void bindDiffer(TextView tv, boolean differ) {
        if (differ) {
            tv.setVisibility(View.VISIBLE);
        }
        else{
            tv.setVisibility(View.INVISIBLE);
        }
    }
    @BindingAdapter({"bind:type"})
    public static void bindType(TextView tv, boolean differ) {
        if (differ) {
            tv.setVisibility(View.VISIBLE);
        }
        else{
            tv.setVisibility(View.INVISIBLE);
        }
    }
    @BindingAdapter({"bind:price"})
    public static void bindPrice(TextView tv, boolean differ) {
        if (differ) {
            tv.setTextColor(Color.parseColor("#ff0000"));
        }
        else{
            tv.setTextColor(Color.parseColor("#32CD32"));
        }
    }
    @BindingAdapter("bind:items")
    public static void bindList(ListView view, final ObservableArrayList<ProductInfo> list) {
        ListAdapter adapter = new ListAdapter(list);
        view.setAdapter(adapter);
        view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("Item Position", Integer.toString(position));
                Intent i = new Intent(view.getContext(), ProductDetail.class);
                // We can also covert it into parceable and pass it
                i.putExtra("thumbnailImageUrl",list.get(position).thumbnailImageUrl.toString());
                i.putExtra("brandName",list.get(position).brandName.toString());
                i.putExtra("originalPrice",list.get(position).originalPrice.toString());
                i.putExtra("price",list.get(position).price.toString());
                i.putExtra("percentOff",list.get(position).percentOff.toString());
                i.putExtra("productName",list.get(position).productName.toString());
                i.putExtra("type",list.get(position).type.toString());
                i.putExtra("productUrl",list.get(position).productUrl.toString());

                view.getContext().startActivity(i);

            }
        });


    }
}

