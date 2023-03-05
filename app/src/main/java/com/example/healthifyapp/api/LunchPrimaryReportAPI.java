package com.example.healthifyapp.api;


import com.example.healthifyapp.report.Root;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LunchPrimaryReportAPI {

        @GET("/api/Users/GetUserDiet?")
        Call<Root> getlunchprimaryReportDataModel(@Query("id") int id,@Query("fromdate") String fromdate,@Query("todate") String todate);
    }

