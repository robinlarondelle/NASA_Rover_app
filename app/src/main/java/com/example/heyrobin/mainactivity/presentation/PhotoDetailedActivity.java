package com.example.heyrobin.mainactivity.presentation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.heyrobin.mainactivity.R;
import com.example.heyrobin.mainactivity.domain.Photo;
import com.squareup.picasso.Picasso;

//This class takes care of the detailed screen of a photo
public class PhotoDetailedActivity extends AppCompatActivity {

    //Variables
    TextView mPhotoCameraName;
    ImageView mPhoto;

    //Log Tag
    private static final String TAG = "PhotoDetailedActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_detailed);
        Log.d(TAG, "onCreate called");

        Bundle extras = getIntent().getExtras();
        Photo photo = (Photo) extras.get("PHOTO");
        Log.d(TAG, "Created a new photo with ID: " + photo.getID() + "and ImageURL: " + photo.getImgURL());

        mPhotoCameraName = (TextView) findViewById(R.id.picture_detailed_cameraname);
        mPhoto = (ImageView) findViewById(R.id.picture_detailed_imageView);
        Log.d(TAG, "Set the XML elements to variables");

        mPhotoCameraName.setText(photo.getCameraName());
        Picasso.get().load(photo.getImgURL()).into(mPhoto);
        Log.d(TAG, "Set the Variable values to the XML elements");
    }
}