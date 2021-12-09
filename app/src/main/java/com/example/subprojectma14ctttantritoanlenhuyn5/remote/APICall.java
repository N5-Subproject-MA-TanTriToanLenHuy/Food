package com.example.subprojectma14ctttantritoanlenhuyn5.remote;

import com.example.subprojectma14ctttantritoanlenhuyn5.model.User;
import com.example.subprojectma14ctttantritoanlenhuyn5.model.UserCreate;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface APICall {

    @POST("auth/login")
    Call<User> userLogin(@Body User user);

    @POST("auth/register")
    Call<UserCreate> userSignUp(@Body UserCreate user);

//    @Headers({
//            "Accept: application/json",
//            "Content-Type: application/x-www-form-urlencoded",
//    })
}
