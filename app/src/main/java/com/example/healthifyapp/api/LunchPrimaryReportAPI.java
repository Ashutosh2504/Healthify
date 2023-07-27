package com.example.healthifyapp.api;


import com.example.healthifyapp.model.DietAnalysisModel;
import com.example.healthifyapp.model.HealthCalculationModel;
import com.example.healthifyapp.report.Root;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LunchPrimaryReportAPI
{
    @GET("/api/Users/GetAnalysisStatus?")
    Call<DietAnalysisModel> getAnalysisStatus(@Query("id") int id);

        @GET("/api/Users/GetUserDiet?")
        Call<Root> getlunchprimaryReportDataModel(@Query("id") int id,@Query("fromdate") String fromdate,@Query("todate") String todate);
}

