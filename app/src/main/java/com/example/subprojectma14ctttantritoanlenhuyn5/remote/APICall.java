package com.example.subprojectma14ctttantritoanlenhuyn5.remote;

import com.example.subprojectma14ctttantritoanlenhuyn5.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface APICall {

    @POST("auth/login")
    Call<User> userLogin(@Body User user);

//    @Headers({
//            "Accept: application/json",
//            "Content-Type: application/x-www-form-urlencoded",
//    })
}
