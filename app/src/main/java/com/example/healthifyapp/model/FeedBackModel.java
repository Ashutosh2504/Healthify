package com.example.healthifyapp.model;

import java.util.List;

public class FeedBackModel {

    public String status;
    public String message;
    public String result;

    public FeedBackModel(String status, String message, String result) {
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

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
