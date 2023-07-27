package com.example.healthifyapp.model;

public class AnalysisStatusResponseModel {

    String primaryAnalysis;
    String secondryAnalysis;
    String tertiaryAnalysis;

    public AnalysisStatusResponseModel() {

    }

    @Override
    public String toString() {
        return "AnalysisStatusResponseModel{" +
                "primaryAnalysis='" + primaryAnalysis + '\'' +
                ", secondryAnalysis='" + secondryAnalysis + '\'' +
                ", tertiaryAnalysis='" + tertiaryAnalysis + '\'' +
                '}';
    }

    public AnalysisStatusResponseModel(String primaryAnalysis, String secondryAnalysis, String tertiaryAnalysis) {
        this.primaryAnalysis = primaryAnalysis;
        this.secondryAnalysis = secondryAnalysis;
        this.tertiaryAnalysis = tertiaryAnalysis;
    }

    public String getPrimaryAnalysis() {
        return primaryAnalysis;
    }

    public void setPrimaryAnalysis(String primaryAnalysis) {
        this.primaryAnalysis = primaryAnalysis;
    }

    public String getSecondryAnalysis() {
        return secondryAnalysis;
    }

    public void setSecondryAnalysis(String secondryAnalysis) {
        this.secondryAnalysis = secondryAnalysis;
    }

    public String getTertiaryAnalysis() {
        return tertiaryAnalysis;
    }

    public void setTertiaryAnalysis(String tertiaryAnalysis) {
        this.tertiaryAnalysis = tertiaryAnalysis;
    }
}
