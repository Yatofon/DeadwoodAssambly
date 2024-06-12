package com.example.deadwoodassambly.model;

import android.graphics.Bitmap;
import android.media.Image;

import java.util.Date;

public class Assembly  {
    private String place;
    private double latitude;
    private double longitude;
    private Date date;
    private String comment;
    private Bitmap image;

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

    public Bitmap getImage() {
        return image;
    }

    public Assembly(String place, double latitude, double longitude, Date date, String comment, Bitmap image) {
        this.place = place;
        this.latitude = latitude;
        this.longitude = longitude;
        this.date = date;
        this.comment = comment;
        this.image = image;
    }
}
