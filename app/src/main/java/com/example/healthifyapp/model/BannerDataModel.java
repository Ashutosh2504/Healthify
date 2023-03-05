package com.example.healthifyapp.model;

import java.util.Date;

public class BannerDataModel {

    public String createdDate;
    public String updatedDate;
    public boolean isDeleted;
    public String remark;
    public int bannerId;
    public String title;
    public String description;
    public String shortDescription;
    public String bannerURL;

    @Override
    public String toString() {
        return "BannerDataModel{" +
                "createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                ", isDeleted=" + isDeleted +
                ", remark='" + remark + '\'' +
                ", bannerId=" + bannerId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", shortDescription='" + shortDescription + '\'' +
                ", bannerURL='" + bannerURL + '\'' +
                '}';
    }

    public BannerDataModel(String createdDate, String updatedDate, boolean isDeleted, String remark, int bannerId, String title, String description, String shortDescription, String bannerURL) {
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.isDeleted = isDeleted;
        this.remark = remark;
        this.bannerId = bannerId;
        this.title = title;
        this.description = description;
        this.shortDescription = shortDescription;
        this.bannerURL = bannerURL;
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

    public int getBannerId() {
        return bannerId;
    }

    public void setBannerId(int bannerId) {
        this.bannerId = bannerId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getBannerURL() {
        return bannerURL;
    }

    public void setBannerURL(String bannerURL) {
        this.bannerURL = bannerURL;
    }
}
