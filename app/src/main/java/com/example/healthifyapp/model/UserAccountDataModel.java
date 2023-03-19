package com.example.healthifyapp.model;

import com.example.healthifyapp.api.UserAccountAPI;

public class UserAccountDataModel {

    public String mobileNo;
    public String userName;
    public String city;
    public String gender;
    public String dob;
    public float height;
    public String heightUnit;
    public float weight;
    public String weightUnit;
    public String medicalCondition;
    public String lifeStyleCategory;
    public String lifeStyleSubCategories;
    public String email;
    public String age;
    public int userAccountId;

    public UserAccountDataModel() {
    }

    public UserAccountDataModel( String mobileNo, String userName, String city, String gender,String dob , float height, String heightUnit, float weight, String weightUnit, String lifeStyleCategory, String lifeStyleSubCategories, String medicalCondition,String email,String age) {
        this.mobileNo = mobileNo;
        this.userName = userName;
        this.city = city;
        this.gender = gender;
        this.dob = dob;
        this.height = height;
        this.heightUnit = heightUnit;
        this.weight = weight;
        this.weightUnit = weightUnit;
        this.medicalCondition = medicalCondition;
        this.lifeStyleCategory = lifeStyleCategory;
        this.lifeStyleSubCategories = lifeStyleSubCategories;
        this.email=email;
        this.age=age;
       // this.userAccountId = userAccountId;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public int getUserAccountId() {
        return userAccountId;
    }

    public void setUserAccountId(int userAccountId) {
        this.userAccountId = userAccountId;
    }

    public UserAccountDataModel(String userName) {
    }


    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getHeightUnit() {
        return heightUnit;
    }

    public void setHeightUnit(String heightUnit) {
        this.heightUnit = heightUnit;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getWeightUnit() {
        return weightUnit;
    }

    public void setWeightUnit(String weightUnit) {
        this.weightUnit = weightUnit;
    }

    public String getMedicalCondition() {
        return medicalCondition;
    }

    public void setMedicalCondition(String medicalCondition) {
        this.medicalCondition = medicalCondition;
    }

    public String getLifeStyleCategory() {
        return lifeStyleCategory;
    }

    public void setLifeStyleCategory(String lifeStyleCategory) {
        this.lifeStyleCategory = lifeStyleCategory;
    }

    public String getLifeStyleSubCategories() {
        return lifeStyleSubCategories;
    }

    public void setLifeStyleSubCategories(String lifeStyleSubCategories) {
        this.lifeStyleSubCategories = lifeStyleSubCategories;
    }

    @Override
    public String toString() {
        return "UserAccountDataModel{" +
                "mobileNo='" + mobileNo + '\'' +
                ", userName='" + userName + '\'' +
                ", city='" + city + '\'' +
                ", gender='" + gender + '\'' +
                ", dob='" + dob + '\'' +
                ", height=" + height +
                ", heightUnit='" + heightUnit + '\'' +
                ", weight=" + weight +
                ", weightUnit='" + weightUnit + '\'' +
                ", medicalCondition='" + medicalCondition + '\'' +
                ", lifeStyleCategory='" + lifeStyleCategory + '\'' +
                ", lifeStyleSubCategories='" + lifeStyleSubCategories + '\'' +
                ", userAccountId=" + userAccountId +
                '}';
    }
}
