package com.zappos.ilovezappos;

import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by Dhruvil on 28-01-2017.
 */

public interface IApiMethod {

    @GET("/Search")
    ApiModel GetProductList(

            @Query("term") String term,
            @Query("key") String key

        );

}