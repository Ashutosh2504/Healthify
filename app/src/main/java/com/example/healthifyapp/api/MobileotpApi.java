package com.example.healthifyapp.api;

import com.example.healthifyapp.model.LifeStyleItemDataModel;
import com.example.healthifyapp.model.MobileDataModel;
import com.example.healthifyapp.model.MobileOtpDataModel;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface MobileotpApi {


   // @POST("/api/UserVerify/OnPostAsync")
    //    Call<JSONObject> createPost(@Body JSONObject mobileDataModel);

   @GET("/api/UserVerify/OnPostAsync?")
   Call<MobileDataModel> createPost(@Query("mobileno") String mobileno);

    @POST("/api/UserVerify/VerifyUser?")
    Call<MobileOtpDataModel> VerifyUser(@Query("mobileno") String mobileno,@Query("otp") String otp);

    @GET("/api/UserVerify/UpdateDeviceToken?")
    Call<MobileDataModel> updateToken(@Query("Mobileno") String mobileno,@Query("token") String token);


}
