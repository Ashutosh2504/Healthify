package com.example.healthifyapp.api;

import com.example.healthifyapp.model.BannerDataModel;
import com.example.healthifyapp.model.DietItemDataModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface BannerApi {

    @GET("/api/Banner/GetBanners")
    Call<List<BannerDataModel>> getBannerDataModel();
}
