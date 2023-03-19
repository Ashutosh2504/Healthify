package com.example.healthifyapp.model;

public class AnalysisStatusModel {

    String status;
    String message;
    AnalysisStatusResponseModel result;

    public AnalysisStatusModel(String status, String message, AnalysisStatusResponseModel result) {
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

    public AnalysisStatusResponseModel getResult() {
        return result;
    }

    public void setResult(AnalysisStatusResponseModel result) {
        this.result = result;
    }
}
