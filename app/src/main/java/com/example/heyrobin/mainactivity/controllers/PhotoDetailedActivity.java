package com.example.heyrobin.mainactivity.controllers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.heyrobin.mainactivity.R;
import com.example.heyrobin.mainactivity.domain.Photo;
import com.example.heyrobin.mainactivity.interfaces.OnPhotoAvailable;
import com.squareup.picasso.Picasso;

import java.io.Serializable;

//This class takes care of the detailed screen of a photo
public class PhotoDetailedActivity extends AppCompatActivity {

    //Variables
    TextView mPhotoId;
    ImageView mPhoto;

    //Log Tag
    private static final String TAG = "PhotoDetailedActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_detailed);
        Log.d(TAG, "onCreate called");

        Bundle extras = getIntent().getExtras();
        Photo photo = (Photo) extras.get("PHOTO");
        Log.d(TAG, "Created a new photo with ID: " + photo.getId() + "and ImageURL: " + photo.getPhotoURL());

        mPhotoId = (TextView) findViewById(R.id.picture_detailed_name);
        mPhoto = (ImageView) findViewById(R.id.picture_detailed_imageView);
        Log.d(TAG, "Set the XML elements to variables");

        mPhotoId.setText(photo.getIdStringFormat());
        Picasso.get().load(photo.getPhotoURL()).into(mPhoto);
        Log.d(TAG, "Set the Variable values to the XML elements");
    }
}