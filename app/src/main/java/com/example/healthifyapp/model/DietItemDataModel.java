package com.example.healthifyapp.model;

public class DietItemDataModel {

    private String createdDate;
    private String updatedDate;
    private Boolean isDeleted;
    private String remark;
    private Integer itemId;
    private String itemName;
    private String kCal;

    public DietItemDataModel(String createdDate, String updatedDate, Boolean isDeleted, String remark, Integer itemId, String itemName, String kCal) {
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.isDeleted = isDeleted;
        this.remark = remark;
        this.itemId = itemId;
        this.itemName = itemName;
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

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getkCal() {
        return kCal;
    }

    public void setkCal(String kCal) {
        this.kCal = kCal;
    }
}
