package com.example.karlo.aplikacija1;

import android.support.v7.app.AppCompatActivity;

import com.example.karlo.aplikacija1.API.RESTAPIInterface;
import com.example.karlo.aplikacija1.API.RetrofitClient;

/**
 * Created by karlo on 24.8.2018..
 */

public class LoginActivity extends AppCompatActivity {


    RESTAPIInterface api = RetrofitClient.getInstance().getAPI();



}
