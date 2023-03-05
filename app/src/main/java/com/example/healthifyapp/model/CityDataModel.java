package com.example.healthifyapp.model;

public class CityDataModel {

    public String text;
    public String value;

    public CityDataModel(String text, String value) {
        this.text = text;
        this.value = value;
    }

    public CityDataModel() {

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
