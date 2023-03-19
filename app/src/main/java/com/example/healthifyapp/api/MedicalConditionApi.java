package com.example.healthifyapp.api;

import com.example.healthifyapp.model.MedicalConditionDataModel;
import com.google.gson.JsonArray;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MedicalConditionApi{

    @GET("/api/MedicalConditions/GetMedicalCondition")
    Call<List<MedicalConditionDataModel> > getmedicalconditionmodel();
}
