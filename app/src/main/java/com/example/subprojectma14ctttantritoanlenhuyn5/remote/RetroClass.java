package com.example.subprojectma14ctttantritoanlenhuyn5.remote;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroClass {

    private static final String URL = "https://n5-apigateway-food.herokuapp.com/";
    private static Retrofit retrofit = null;

    private static Retrofit getClient(){

        if(retrofit == null){
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            return new Retrofit
                    .Builder()
                    .baseUrl(URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }

    public static APICall getAPICall(){
        return getClient().create(APICall.class);
    }

}
