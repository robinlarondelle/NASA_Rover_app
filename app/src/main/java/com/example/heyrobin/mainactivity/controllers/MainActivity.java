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
    private LinearLayoutManager mLayoutManager;
    private PhotoAdapter mPhotoAdapter;
    private ArrayList<Photo> mPhotos;


    //Method used when the activity is created
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Standard code
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Log
        Log.d(TAG, "onCreate called");

        //Custom code

        mPhotos = new ArrayList<>();

        //Setting up the data from the api
        String[] params = {Api.getApiNASA()};
        Log.d(TAG, "API key: " + Api.getApiNASA());

        //Executing the photo task
        PhotoTask photoTask = new PhotoTask(this);
        photoTask.execute(params);

        //Creating the RecyclerView:
        mRecyclerView = (RecyclerView) findViewById(R.id.content_main_recycleview);
        mRecyclerView.setHasFixedSize(true);

        //Setting the layout manager to the RecycleView
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        //Setting the adapter
        mPhotoAdapter = new PhotoAdapter(mPhotos/*, this*/);
        mRecyclerView.setAdapter(mPhotoAdapter);
    }


    @Override
    public void OnPhotoAvailable(Serializable serializable) {
        Photo photo = (Photo) serializable;
        Log.d(TAG, "OnPhotoAvailable was called. retrieved photo: " + photo.getId());

        mPhotos.add(photo);
        mPhotoAdapter.notifyDataSetChanged();
        Log.d(TAG, "Photo "+photo.getId()+" added to the ArrayList");
    }
}
