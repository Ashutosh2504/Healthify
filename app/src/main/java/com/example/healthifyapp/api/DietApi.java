package com.example.healthifyapp.api;

import com.example.healthifyapp.model.DietDataModel;
import com.example.healthifyapp.model.DietDataModel2;
import com.example.healthifyapp.model.DietItemDataModel;
import com.example.healthifyapp.model.FoodDietModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface DietApi {
    @POST("/api/Users/AddDiet")
    Call<DietDataModel.Root> createPost(@Body DietDataModel.Root dietDataModel);


    @GET("/api/Items")
    Call<ArrayList<DietItemDataModel>> getdietitemDataModel();

    @GET("/api/Items")
    Call<ArrayList<FoodDietModel>> getFoodItemDataModel();


}
