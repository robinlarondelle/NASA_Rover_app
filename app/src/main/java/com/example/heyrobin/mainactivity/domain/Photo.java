package com.example.heyrobin.mainactivity.domain;

import android.util.Log;

import java.io.Serializable;

/**
 * Created by HeyRobin on 13-3-2018.
 */

public class Photo implements Serializable {

    private int id;
    private String photoURL;

    //Log
    private final static String TAG = "Photo Class";

    public Photo(int id, String photoURL)   {
        Log.d(TAG, "Photo Class called with the following id: " +id);
        this.id = id;
        this.photoURL = photoURL;
    }


    //Getters and Setters
    public int getId() {
        return id;
    }

    public String getIdStringFormat() {
        return "Image ID: "+ id;
    }

    public String getPhotoURL()  {
        return photoURL;
    }
}
