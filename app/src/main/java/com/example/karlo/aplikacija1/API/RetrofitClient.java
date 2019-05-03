package com.example.karlo.aplikacija1.API;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by karlo on 23.8.2018..
 */

public class RetrofitClient {

    //(1)
    //    JSON hosting service for any Android devices, GET/DELETE
    //    public String BASE_URL = "https://api.jsonbin.io/b/";

    //(2)
    //    localhost API for connected external Android Device, RESTful
    //    Chrome url:  chrome://inspect/#devices
    //            ->    port forwarding...
    //            ->    add port(e.g.5047, localhost:5047)
    //            ->    enable port forwarding
    private static final String BASE_URL = "http://localhost:5047/api/";

    private static RetrofitClient mInstance;
    private Retrofit retrofit;

    public Gson gsonSetDateFormatFromJSON = new GsonBuilder()
            .setDateFormat("dd/MM/yyyy HH:mm")
            .create();

    private RetrofitClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl(RESTAPIInterface.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gsonSetDateFormatFromJSON))
                .build();
    }

    public static synchronized RetrofitClient getInstance() {
        if (mInstance == null) {
            mInstance = new RetrofitClient();
        }
        return mInstance;
    }

    public RESTAPIInterface getAPI() {
        return retrofit.create(RESTAPIInterface.class);
    }

}
