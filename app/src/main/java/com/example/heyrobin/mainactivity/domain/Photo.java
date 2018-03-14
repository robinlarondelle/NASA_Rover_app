package com.example.heyrobin.mainactivity.domain;

import android.util.Log;

import java.io.Serializable;

public class Photo implements Serializable {


    //Variables
    private String ID;
    private String cameraName;
    private String robotName;
    private String photoDate;
    private String totalPhotos;
    private String imgURL;
    private static final String TAG = "PersonClass";

    //Constructor
    public Photo(String ID, String cameraName, String robotName, String photoDate, String totalPhotos, String imgURL) {
        this.ID = ID;
        this.cameraName = cameraName;
        this.robotName = robotName;
        this.photoDate = photoDate;
        this.totalPhotos = totalPhotos;
        this.imgURL = imgURL;

        Log.d(TAG, "Constructor called");
    }

    //INNER CLASS
        //Person builder
        //Builder pattern
        public static class PhotoBuilder {

            private String ID;
            private String cameraName;
            private String robotName;
            private String imageURL;
            private String imageDate;
            private String imageTotal;
            private static final String TAG = "PhotoBuilder";

            public PhotoBuilder(String ID, String cameraName, String robotName, String imageDate, String imageTotal, String imageURL) {
                this.ID = ID;
                this.cameraName = cameraName;
                this.robotName = robotName;
                this.imageDate = imageDate;
                this.imageTotal = imageTotal;
                this.imageURL = imageURL;
                Log.d(TAG, "PhotoBuilder constructor called");
            }

            public PhotoBuilder setID(String ID) {
                this.ID = "Image ID: " + ID;
                return this;
            }

            public PhotoBuilder setCameraName(String cameraName) {
                this.cameraName = cameraName;
                return this;
            }

            public PhotoBuilder setRobotName(String robotName) {
                this.robotName = robotName;
                return this;
            }

            public PhotoBuilder setImageURL(String imageURL) {
                this.imageURL = imageURL;
                return this;
            }

            public PhotoBuilder setImageInformation(String imageDate, String imageTotal) {
                this.imageDate = imageDate;
                this.imageTotal = imageTotal;
                return this;
            }

            public Photo build() {
                return new Photo(ID, cameraName, robotName, imageDate, imageTotal, imageURL);
            }

        }

    public String getID() {
        return ID;
    }

    //GETTERS AND SETTERS
    public void setID(String ID) {
        this.ID = ID;
    }

    public String getCameraName() {
        return cameraName;
    }

    public void setCameraName(String cameraName) {
        this.cameraName = cameraName;
    }

    public String getRobotName() {
        return robotName;
    }

    public void setRobotName(String robotName) {
        this.robotName = robotName;
    }

    public String getPhotoDate() {
        return photoDate;
    }

    public void setPhotoDate(String photoDate) {
        this.photoDate = photoDate;
    }

    public String getTotalPhotos() {
        return totalPhotos;
    }

    public void setTotalPhotos(String totalPhotos) {
        this.totalPhotos = totalPhotos;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }
}
