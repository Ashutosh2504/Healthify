package com.example.healthifyapp.model;

public class WaterTrakerModel {
    String createdDate;
    String updatedDate;
    int waterTrakerId;
    int userAccountId;
    String todayDate;
    String todayTime;
    String  quanity;
    String isDeleted;
    String remark;

    public WaterTrakerModel(String createdDate, String updatedDate, int waterTrakerId, int userAccountId, String todayDate, String todayTime, String quanity, String isDeleted, String remark) {
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.waterTrakerId = waterTrakerId;
        this.userAccountId = userAccountId;
        this.todayDate = todayDate;
        this.todayTime = todayTime;
        this.quanity = quanity;
        this.isDeleted = isDeleted;
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "WaterTrakerModel{" +
                "createdDate='" + createdDate + '\'' +
                ", updatedDate='" + updatedDate + '\'' +
                ", waterTrakerId=" + waterTrakerId +
                ", userAccountId=" + userAccountId +
                ", todayDate='" + todayDate + '\'' +
                ", todayTime='" + todayTime + '\'' +
                ", quanity=" + quanity +
                ", isDeleted='" + isDeleted + '\'' +
                ", remark='" + remark + '\'' +
                '}';
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

    public int getWaterTrakerId() {
        return waterTrakerId;
    }

    public void setWaterTrakerId(int waterTrakerId) {
        this.waterTrakerId = waterTrakerId;
    }

    public int getUserAccountId() {
        return userAccountId;
    }

    public void setUserAccountId(int userAccountId) {
        this.userAccountId = userAccountId;
    }

    public String getTodayDate() {
        return todayDate;
    }

    public void setTodayDate(String todayDate) {
        this.todayDate = todayDate;
    }

    public String getTodayTime() {
        return todayTime;
    }

    public void setTodayTime(String todayTime) {
        this.todayTime = todayTime;
    }

    public String getQuanity() {
        return quanity;
    }

    public void setQuanity(String quanity) {
        this.quanity = quanity;
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
