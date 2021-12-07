package com.example.subprojectma14ctttantritoanlenhuyn5.remote;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroClass {

    private static final String URL = "";

    private static Retrofit getRetrofitInstance(){

        Gson gson = new GsonBuilder().setLenient().create();

        return new Retrofit
                .Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    public static APICall getAPICall(){
        return getRetrofitInstance().create(APICall.class);
    }

}
