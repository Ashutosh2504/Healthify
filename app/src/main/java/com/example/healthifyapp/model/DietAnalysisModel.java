package com.example.healthifyapp.model;

import java.util.List;

public class DietAnalysisModel {
    private String status, message;
    private List<AnalysisStatusResponseModel> result;

    public DietAnalysisModel(String status, String message, List<AnalysisStatusResponseModel> result) {
        this.status = status;
        this.message = message;
        this.result = result;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<AnalysisStatusResponseModel> getResult() {
        return result;
    }

    public void setResult(List<AnalysisStatusResponseModel> result) {
        this.result = result;
    }
}