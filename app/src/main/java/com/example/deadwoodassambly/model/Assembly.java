package com.example.deadwoodassambly.model;

import java.util.Date;

public class Assembly
{
    private String place;
    private double latitude;
    private double longitude;
    private Date date;
    private String comment;
    private String image;

    public String getPlace() {
        return place;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public Date getDate() {
        return date;
    }

    public String getComment() {
        return comment;
    }

    public String getImage() {
        return image;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Assembly(String place, double latitude, double longitude, Date date, String comment, String image) {
        this.place = place;
        this.latitude = latitude;
        this.longitude = longitude;
        this.date = date;
        this.comment = comment;
        this.image = image;
    }
}
