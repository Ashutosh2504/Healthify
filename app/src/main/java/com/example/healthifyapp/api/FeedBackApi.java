package com.example.healthifyapp.api;

import com.example.healthifyapp.model.FeedBackModel;
import com.example.healthifyapp.model.RatingModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface FeedBackApi {

    @POST("/api/RateUs")
    Call<FeedBackModel> createPost(@Body RatingModel feedBackModel);
}
