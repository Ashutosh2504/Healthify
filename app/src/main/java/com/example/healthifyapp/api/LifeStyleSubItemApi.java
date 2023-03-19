package com.example.healthifyapp.api;

import com.example.healthifyapp.model.LifeStyleItemDataModel;
import com.example.healthifyapp.model.LifeStyleSubItemModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface LifeStyleSubItemApi {
    @GET("/api/LIfeStyleCategories/GetLIfeStyleSubCategory?id=1")
    Call<List<LifeStyleSubItemModel>> getlifestylesubitemmodel();

}
