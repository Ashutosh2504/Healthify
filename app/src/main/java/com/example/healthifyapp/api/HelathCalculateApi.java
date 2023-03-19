package com.example.healthifyapp.api;

import com.example.healthifyapp.model.CalculationDataModel;
import com.example.healthifyapp.model.HealthCalculationModel;
import com.example.healthifyapp.model.MobileDataModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface HelathCalculateApi {

    @GET("/api/Calculation/GetBMI?")
    Call<HealthCalculationModel> getbmi(@Query("id") int id);

    @GET("/api/Calculation/GetIBW?")
    Call<HealthCalculationModel> getibw(@Query("id") int id);

    @GET("/api/Calculation/GetBFP?")
    Call<HealthCalculationModel> getbfp(@Query("id") int id);

    @GET("/api/Calculation/GetBSA?")
    Call<HealthCalculationModel> getbsa(@Query("id") int id);

    @GET("/api/Calculation/GetLBW?")
    Call<HealthCalculationModel> getlbw(@Query("id") int id);


}
