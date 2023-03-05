package com.example.healthifyapp.api;

import com.example.healthifyapp.model.CityDataModel;
import com.example.healthifyapp.model.LifeStyleSubItemModel;
import com.example.healthifyapp.model.MobileDataModel;
import com.example.healthifyapp.model.MobileOtpDataModel;
import com.example.healthifyapp.model.UserAccountDataModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UserAccountAPI {


    @POST("/api/UserVerify/UpdateUserDetails")
    Call<UserAccountDataModel> createPost(@Body UserAccountDataModel userAccountDataModel);



    @GET("/api/Cities/GetCities")
    Call<ArrayList<CityDataModel>> getcitydatamodel();

    @GET("/api/UserVerify/GetUserDetails?")
    Call<MobileOtpDataModel> getUserDetails(@Query("id") int userAccountId);

}
