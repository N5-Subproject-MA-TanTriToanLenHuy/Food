package com.example.subprojectma14ctttantritoanlenhuyn5.remote;

import com.example.subprojectma14ctttantritoanlenhuyn5.model.JWTToken;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APICall {

    @FormUrlEncoded
    @POST("")
    Call<JWTToken> userLogin(@Field("username") String username, @Field("password") String password);

}
