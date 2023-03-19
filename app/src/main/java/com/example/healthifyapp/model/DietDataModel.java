package com.example.healthifyapp.model;

import java.util.ArrayList;
import java.util.List;

public class DietDataModel {
    public String item;
    public String quantity;
    public String kCal;


    public DietDataModel(String quantity,String item,String kCal) {
        this.item = item;
        this.quantity = quantity;
        this.kCal = kCal;

    }


    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getkCal() {
        return kCal;
    }

    public void setkCal(String kCal) {
        this.kCal = kCal;
    }


    public static class Root {
        public int userID;
        public String dietAnalysisType;
        public String dietTime;
        public String dietType;
        public String date;
        public ArrayList<DietDataModel> dietAnalysisDetailList;




        public Root(int userID,String dietAnalysisType,String dietTime,String dietType,String date,ArrayList <DietDataModel> dietAnalysisDetailList) {
            this.userID = userID;
            this.dietAnalysisType = dietAnalysisType;
            this.dietTime = dietTime;
            this.dietType = dietType;
            this.date = date;
            this.dietAnalysisDetailList = dietAnalysisDetailList;
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


    }

}

