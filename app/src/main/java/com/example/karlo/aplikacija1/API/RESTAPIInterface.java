package com.example.karlo.aplikacija1.API;

import com.example.karlo.aplikacija1.Model.BloodType;
import com.example.karlo.aplikacija1.Model.BloodUnit;
import com.example.karlo.aplikacija1.Model.Location;
import com.example.karlo.aplikacija1.Model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by karlo on 14.8.2018..
 */

public interface RESTAPIInterface {
    //(1)
    //    JSON hosting service for any Android devices
    //    public String BASE_URL = "https://api.jsonbin.io/b/";

    //(2)
    //    localhost API for connected external Android Device
    //    Chrome url:  chrome://inspect/#devices
    //            ->    port forwarding...
    //            ->    add port(e.g.5047, localhost:5047)
    //            ->    enable port forwarding
    String BASE_URL = "http://localhost:5047/api/";

    //(1)
    //@GET("5b7810f4f5ef92564b0a369c")
    //(2)
    @GET("LocationData")
    Call<List<Location>> getLocations();

    //(1)
    //@GET("5b7810f4f5ef92564b0a369c")
    //(2)
    @GET("BloodTypeData")
    Call<List<BloodType>> getBloodTypes();

    @GET("BloodUnitData")
    Call<List<BloodUnit>> getBloodUnits();

    @GET("UserData")
    Call<List<User>> getUsers();


}
