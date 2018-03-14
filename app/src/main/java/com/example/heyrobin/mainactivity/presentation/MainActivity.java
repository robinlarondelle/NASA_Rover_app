package com.example.heyrobin.mainactivity.presentation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.heyrobin.mainactivity.R;
import com.example.heyrobin.mainactivity.util.Tags;
import com.example.heyrobin.mainactivity.adapters.PhotoAdapter;
import com.example.heyrobin.mainactivity.api.PhotoTask;
import com.example.heyrobin.mainactivity.domain.Photo;
import com.example.heyrobin.mainactivity.adapters.OnPhotoAvailable;
import java.util.ArrayList;


//Main activity class with RecycleView and Dropdown
public class MainActivity
        extends AppCompatActivity
        implements OnPhotoAvailable,
        AdapterView.OnItemSelectedListener {

    //Variables
    private static final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<Photo> mPhotoList;
    private Spinner mSpinner;
    private static final String[]mPaths = {"Select camera...", "fhaz", "rhaz", "mast", "chemcam", "mahli", "navcam"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate called.");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPhotoList = new ArrayList<>();

        //RecyclerView
        mRecyclerView = (RecyclerView) findViewById(R.id.content_main_recycleview);
        executeGetRequest(null, Tags.SOL_AMOUNT);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new PhotoAdapter(mPhotoList, this);
        mRecyclerView.setAdapter(mAdapter);
        Log.d(TAG, "RecyclerView ready");

        //Spinner
        mSpinner = (Spinner)findViewById(R.id.spinner);
        ArrayAdapter<String>adapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_spinner_item, mPaths);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(adapter);
        mSpinner.setOnItemSelectedListener(this);
        Log.d(TAG, "Spinner Set");
    }

    //Callback from PhotoTask when a new Photo is available
    @Override
    public void OnPhotoAvailable(Photo photo) {
        Log.d(TAG, "OnPhotoAvailable called.");

        mPhotoList.add(photo);
        mAdapter.notifyDataSetChanged();
        Log.d(TAG, "Added new photo to the arraylist");
    }

    //Mandatory method
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {}

    //Create the correct request based on the spinner position
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
        Log.d(TAG, "onItemSelected called.");

        switch (position) {
            case 0:
                break;
            case 1:
                executeGetRequest(mPaths[1].toString(), Tags.SOL_AMOUNT);
                break;
            case 2:
                executeGetRequest(mPaths[2].toString(), Tags.SOL_AMOUNT);
                break;
            case 3:
                executeGetRequest(mPaths[3].toString(), Tags.SOL_AMOUNT);
                break;
            case 4:
                executeGetRequest(mPaths[4].toString(), Tags.SOL_AMOUNT);
                break;
            case 5:
                executeGetRequest(mPaths[5].toString(), Tags.SOL_AMOUNT);
                break;
            case 6:
                executeGetRequest(mPaths[6].toString(), Tags.SOL_AMOUNT);
                break;
        }
    }

    private void executeGetRequest(String cameraPoint, int solAmount) {
        Log.d(TAG, "executeGetRequest called.");

        String camera = cameraPoint;
        int amount = solAmount;
        String[] params = null;

        if (camera == null) {
            params = new String[]{"https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?sol=" + amount + "&api_key=" + Tags.API_KEY};
        } else {
            params = new String[]{"https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?sol=" + amount + "&camera=" + camera + "&api_key=" + Tags.API_KEY};
        }

        PhotoTask photoTask = new PhotoTask(this);
        mPhotoList.clear();
        photoTask.execute(params);
        Log.d(TAG, "Request executed");
    }
}
