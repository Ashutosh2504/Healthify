package com.example.healthifyapp.model;

import java.util.ArrayList;

public class DietDataModel2 {

    public int userID;
    public String dietAnalysisType;
    public String dietTime;
    public String dietType;
    public String date;
    public ArrayList<DietDataModel> dietDataModels;




    /*public DietDataModel2(String dietType, String date, String dietTime) {

        this.userID = userID;
        this.dietAnalysisType = dietAnalysisType;
        this.dietTime = dietTime;
        this.dietType = dietType;
        this.date = date;
        this.dietDataModels = dietDataModels;
    }*/

    public DietDataModel2(int userID, String dietAnalysisType, String dietTime, String dietType,
                          String date, ArrayList<DietDataModel> dietDataModels) {
        this.userID = userID;
        this.dietAnalysisType = dietAnalysisType;
        this.dietTime = dietTime;
        this.dietType = dietType;
        this.date = date;
        this.dietDataModels = dietDataModels;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getDietAnalysisType() {
        return dietAnalysisType;
    }

    public void setDietAnalysisType(String dietAnalysisType) {
        this.dietAnalysisType = dietAnalysisType;
    }

    public String getDietTime() {
        return dietTime;
    }

    public void setDietTime(String dietTime) {
        this.dietTime = dietTime;
    }

    public String getDietType() {
        return dietType;
    }

    public void setDietType(String dietType) {
        this.dietType = dietType;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ArrayList<DietDataModel> getDietDataModels() {
        return dietDataModels;
    }

    public void setDietDataModels(ArrayList<DietDataModel> dietDataModels) {
        this.dietDataModels = dietDataModels;
    }
}
