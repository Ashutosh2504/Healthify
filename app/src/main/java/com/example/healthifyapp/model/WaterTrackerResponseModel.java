package com.example.healthifyapp.model;

public class WaterTrackerResponseModel {
    String  createdDate;
    String updatedDate;
    String isDeleted;
    String remark;
    int waterTrakerId;
    int userAccountId;
    String todayDate;
    String todayTime;
    String quanity;


    public WaterTrackerResponseModel(String remark, int waterTrakerId, int userAccountId, String todayDate, String todayTime, String quanity) {
        this.remark = remark;
        this.waterTrakerId = waterTrakerId;
        this.userAccountId = userAccountId;
        this.todayDate = todayDate;
        this.todayTime = todayTime;
        this.quanity = quanity;
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

    @Override
    public String toString() {
        return "WaterTrackerResponseModel{" +
                "createdDate='" + createdDate + '\'' +
                ", updatedDate='" + updatedDate + '\'' +
                ", isDeleted='" + isDeleted + '\'' +
                ", remark='" + remark + '\'' +
                ", waterTrakerId=" + waterTrakerId +
                ", userAccountId=" + userAccountId +
                ", todayDate='" + todayDate + '\'' +
                ", todayTime='" + todayTime + '\'' +
                ", quanity='" + quanity + '\'' +
                '}';
    }
}
