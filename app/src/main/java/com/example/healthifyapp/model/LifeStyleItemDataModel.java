package com.example.healthifyapp.model;

public class LifeStyleItemDataModel {

    private String text;
    private String value;

    public LifeStyleItemDataModel(String text, String value) {
        this.text = text;
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
