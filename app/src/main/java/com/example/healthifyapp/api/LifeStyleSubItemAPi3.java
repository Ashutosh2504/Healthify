package com.example.healthifyapp.api;

import com.example.healthifyapp.model.LifeStyleSubItemModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface LifeStyleSubItemAPi3 {
    @GET("/api/LIfeStyleCategories/GetLIfeStyleSubCategory?id=3")
    Call<List<LifeStyleSubItemModel>> getlifestylesubitemmodel3();
}
