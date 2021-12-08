package com.example.subprojectma14ctttantritoanlenhuyn5.remote;

import java.util.Set;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APICall {

    @FormUrlEncoded
    @POST("/auth/login")
    @Headers({
            "Accept: application/json",
            "Content-Type: application/x-www-form-urlencoded",
    })
    Call<ResponseBody> userLogin(@Field("username") String username, @Field("password") String password);
}
