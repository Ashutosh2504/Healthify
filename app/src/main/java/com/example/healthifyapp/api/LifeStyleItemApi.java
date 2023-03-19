package com.example.healthifyapp.api;

import com.example.healthifyapp.model.LifeStyleItemDataModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface LifeStyleItemApi {

    @GET("/api/LIfeStyleCategories/GetLIfeStyleCategory")
    Call<List<LifeStyleItemDataModel>> getlifestyleitemmodel();
}
