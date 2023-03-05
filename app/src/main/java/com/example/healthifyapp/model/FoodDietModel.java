package com.example.healthifyapp.model;

public class FoodDietModel {

    int id;
    String itemName;
    String kCal;
    String createdDate;
    String updatedDate;
    boolean isDeleted;
    String remark;

    public FoodDietModel(int id, String itemName, String kCal, String createdDate,
                         String updatedDate, boolean isDeleted, String remark) {
        this.id = id;
        this.itemName = itemName;
        this.kCal = kCal;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.isDeleted = isDeleted;
        this.remark = remark;
    }

    public FoodDietModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getKCal() {
        return kCal;
    }

    public void setKCal(String kcal) {
        this.kCal = kCal;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
