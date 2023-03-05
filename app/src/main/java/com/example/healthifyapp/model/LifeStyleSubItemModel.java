package com.example.healthifyapp.model;

public class LifeStyleSubItemModel {

    private String text;
    private String value;
    private boolean isSelected;

    public LifeStyleSubItemModel(String text, String value) {
        this.text = text;
        this.value = value;
    }

    public  String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getValue() {
        return value;
    }

   // changes made by ashutosh
    public void setValue(String value) {
        this.value = value;
    }

    public boolean getSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

}
