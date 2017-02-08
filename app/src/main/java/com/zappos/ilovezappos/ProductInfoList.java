package com.zappos.ilovezappos;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.databinding.ObservableArrayList;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RelativeLayout;

import retrofit.RestAdapter;

/**
 * Created by Dhruvil on 28-01-2017.
 */

public class ProductInfoList {

    public ObservableArrayList<ProductInfo> productList = new ObservableArrayList<>();
    ProgressDialog loading = null;
    String searchText,term="";
    Activity activity;
    Context context;

    public ProductInfoList(Context context, Activity activity) {
        this.context=context;
        this.activity=activity;
    }

    /* api call when clicked on search edittext*/
    public void onText(View v) {
        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 1);
        }
        apiCall();
    }

    /* api call when clicked on search button*/
    public void search(View v) {
        apiCall();
    }

    /* Handles the API call*/
    public void apiCall(){
        productList.clear();
        EditText searchEdittext = (EditText) activity.findViewById(R.id.search_edittext);
        searchText = searchEdittext.getText().toString();
        if(!searchText.equals("")&& !searchText.equals(term)){
            productList.clear();
            term = searchText;
            loading = ProgressDialog.show(activity, "", "Please wait...", false, false);
            GetProductInfo task = new GetProductInfo();
            task.execute();
        }

    }

    /* Adds the product info to the observable arraylist*/
    private void add(ProductInfo info) {
        productList.add(info);
    }

    /* API Implementation*/
    private class GetProductInfo extends AsyncTask<Void, Void,
            ApiModel> {
        RestAdapter restAdapter;

        @Override
        protected void onPreExecute() {
            restAdapter = new RestAdapter.Builder()
                    .setEndpoint(Information.URL)
                    .build();
        }

        @Override
        protected ApiModel doInBackground(Void... params) {
            try {
                IApiMethod methods = restAdapter.create(IApiMethod.class);
                ApiModel curators = methods.GetProductList(term, Information.KEY);

                return curators;
            } catch (Exception E) {
                Log.e("Api Call Exception", E.toString());
                return null;
            }
        }


        @Override
        protected void onPostExecute(ApiModel curators) {
            RelativeLayout rl = (RelativeLayout) activity.findViewById(R.id.retry_layout);
            loading.dismiss();
            View view = activity.getCurrentFocus();
            if (view != null) {
                InputMethodManager imm = (InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
            try {
                if (curators.statusCode.equals("200")) {
                    Log.e("Api call", "Successful");
                    rl.setVisibility(View.GONE);
                    for (ApiModel.results dataset : curators.results) {

                        if (!dataset.price.toString().equals(dataset.originalPrice.toString())) {
                            add(new ProductInfo(dataset.thumbnailImageUrl, dataset.brandName, dataset.originalPrice, dataset.price, dataset.percentOff, dataset.productName, "Sale", true, dataset.productUrl));
                        } else {
                            add(new ProductInfo(dataset.thumbnailImageUrl, dataset.brandName, dataset.originalPrice, dataset.price, dataset.percentOff, dataset.productName, "Sale", false, dataset.productUrl));
                        }
                    }
                } else {
                    rl.setVisibility(View.VISIBLE);
                }
            }catch (Exception E) {
                Log.e("Display Exception", E.toString());
                rl.setVisibility(View.VISIBLE);
                rl.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        loading = ProgressDialog.show(activity, "", "Please wait...", false, false);
                        GetProductInfo task = new GetProductInfo();
                        task.execute();
                    }
                });
            }
        }

    }

}
