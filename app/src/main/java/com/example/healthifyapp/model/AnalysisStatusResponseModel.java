package com.example.healthifyapp.model;

public class AnalysisStatusResponseModel {

    boolean primaryAnalysis;
    boolean secondryAnalysis;
    boolean tertiaryAnalysis;

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

    public AnalysisStatusResponseModel(boolean primaryAnalysis, boolean secondryAnalysis, boolean tertiaryAnalysis) {
        this.primaryAnalysis = primaryAnalysis;
        this.secondryAnalysis = secondryAnalysis;
        this.tertiaryAnalysis = tertiaryAnalysis;
    }

    public boolean isPrimaryAnalysis() {
        return primaryAnalysis;
    }

    public void setPrimaryAnalysis(boolean primaryAnalysis) {
        this.primaryAnalysis = primaryAnalysis;
    }

    public boolean isSecondryAnalysis() {
        return secondryAnalysis;
    }

    public void setSecondryAnalysis(boolean secondryAnalysis) {
        this.secondryAnalysis = secondryAnalysis;
    }

    public boolean isTertiaryAnalysis() {
        return tertiaryAnalysis;
    }

    public void setTertiaryAnalysis(boolean tertiaryAnalysis) {
        this.tertiaryAnalysis = tertiaryAnalysis;
    }
}
