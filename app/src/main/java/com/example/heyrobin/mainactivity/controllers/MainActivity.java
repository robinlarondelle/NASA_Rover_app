package com.example.heyrobin.mainactivity.controllers;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.heyrobin.mainactivity.R;
import com.example.heyrobin.mainactivity.adapters.PhotoAdapter;
import com.example.heyrobin.mainactivity.api.Api;
import com.example.heyrobin.mainactivity.api.PhotoTask;
import com.example.heyrobin.mainactivity.domain.Photo;
import com.example.heyrobin.mainactivity.interfaces.OnPhotoAvailable;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity
        extends AppCompatActivity
        implements OnPhotoAvailable{

    //Variables
    private static final String TAG = "MainActivity";
    private RecyclerView mRecyclerView;
    private ArrayList<Photo> mPhotos = new ArrayList<>();
    private PhotoAdapter mPhotoAdapter = new PhotoAdapter(mPhotos, this);
    private LinearLayoutManager mLayoutManager;
    private PhotoTask photoTask = new PhotoTask(this);





    //Method used when the activity is created
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Standard code
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Log
        Log.d(TAG, "onCreate called");

        //Custom code

        //Creating the RecyclerView:
        mRecyclerView = (RecyclerView) findViewById(R.id.content_main_recycleview);
        mRecyclerView.setHasFixedSize(true);

        //Setting the layout manager to the RecycleView
        mLayoutManager = new LinearLayoutManager(MainActivity.this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        //Setting up the data from the api
        String[] params = {Api.getApiNASA()};
        Log.d(TAG, "API key: " + Api.getApiNASA());

        Log.d(TAG, "Starting a new phototask");
        PhotoTask photoTask = new PhotoTask(this);
        photoTask.execute(params);

        mPhotoAdapter.notifyDataSetChanged();
        //Setting the adapter
        mRecyclerView.setAdapter(mPhotoAdapter);
        Log.d(TAG, "RecyclerView ready");
    }


    @Override
    public void OnPhotoAvailable(Serializable serializable) {
        Photo photo = (Photo) serializable;
        Log.d(TAG, "OnPhotoAvailable was called. retrieved photo: " + photo.getId());

        mPhotos.add(photo);
        mPhotoAdapter.notifyDataSetChanged();
        Log.d(TAG, "Photo "+photo.getId()+" added to the ArrayList");
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "OnSaveInstanceState called.");

        // Save selected date and photo array
        outState.putSerializable("PHOTOS", mPhotos);
        Log.d(TAG, "Stored the photo's, size: " + mPhotos.size());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(TAG, "OnRestoreInstanceState called");

        // Restore photo array
        mPhotos = (ArrayList<Photo>) savedInstanceState.getSerializable("PHOTOS");
        Log.d(TAG, "photos restored, size: " + mPhotos.size());
    }

}
