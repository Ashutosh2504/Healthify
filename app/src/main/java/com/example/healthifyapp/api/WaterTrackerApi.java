package com.example.healthifyapp.api;

import com.example.healthifyapp.model.AnalysisStatusModel;
import com.example.healthifyapp.model.EnergyModel;
import com.example.healthifyapp.model.UserAccountDataModel;
import com.example.healthifyapp.model.WaterTrackerModel;
import com.example.healthifyapp.model.WaterTrackerResponseModel;
import com.example.healthifyapp.model.WaterTrakerModel;
import com.example.healthifyapp.model.WatetIntakeModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface WaterTrackerApi {
    @POST("/api/WaterTraker/PostItem")
    Call<WaterTrackerModel> createPost(@Body WaterTrackerResponseModel waterTrackerResponseModel);

    @GET("/api/WaterTraker/GetWatetIntake")
    Call<WatetIntakeModel> getWaterIntakeModel(@Query("userid") int userid);

    @GET("/api/WaterTraker/GetItems")
    Call<List<WaterTrakerModel>> getWaterTracker(@Query("userid") int userid);

    @GET("/api/Users/GetAnalysisStatus")
    Call<AnalysisStatusModel> getAnalysisStatus(@Query("id") int id);

    @GET("/api/Calculation/GetEER")
    Call<EnergyModel> getEER(@Query("id") int id);
}
