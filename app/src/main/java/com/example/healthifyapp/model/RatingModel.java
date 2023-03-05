package com.example.healthifyapp.model;

public class RatingModel {


    public int id;
    public String patientID;
    public String rating;
    public String feedback;
    public String createdDate;

    public RatingModel(int id, String patientID, String rating, String feedback) {
        this.id = id;
        this.patientID = patientID;
        this.rating = rating;
        this.feedback = feedback;

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPatientID() {
        return patientID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "RatingModel{" +
                "id=" + id +
                ", patientID='" + patientID + '\'' +
                ", rating='" + rating + '\'' +
                ", feedback='" + feedback + '\'' +
                ", createdDate='" + createdDate + '\'' +
                '}';
    }
}
