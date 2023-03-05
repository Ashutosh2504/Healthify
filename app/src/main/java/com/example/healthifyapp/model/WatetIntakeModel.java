package com.example.healthifyapp.model;

public class WatetIntakeModel {

   public String totalWater;
   public String remainingWater;
   public String totalWaterIntake;

    public WatetIntakeModel(String totalWater, String remainingWater, String totalWaterIntake) {

        this.totalWater = totalWater;
        this.remainingWater = remainingWater;
        this.totalWaterIntake = totalWaterIntake;
    }

    public WatetIntakeModel() {

    }



    public String getTotalWater() {
        return totalWater;
    }

    public void setTotalWater(String totalWater) {
        this.totalWater = totalWater;
    }

    public String getRemainingWater() {
        return remainingWater;
    }

    public void setRemainingWater(String remainingWater) {
        this.remainingWater = remainingWater;
    }

    public String getTotalWaterIntake() {
        return totalWaterIntake;
    }

    public void setTotalWaterIntake(String totalWaterIntake) {
        this.totalWaterIntake = totalWaterIntake;
    }
}
