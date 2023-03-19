package com.example.healthifyapp.model;

public class MobileDataModel {
    public String mobileno;
    public String status;
    public String message;
    public String result;

    public MobileDataModel(String mobileno, String status, String message, String result) {
        this.mobileno = mobileno;
        this.status = status;
        this.message = message;
        this.result = result;
    }

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
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

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
