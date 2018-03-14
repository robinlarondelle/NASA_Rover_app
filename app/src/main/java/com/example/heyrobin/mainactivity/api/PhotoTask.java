package com.example.heyrobin.mainactivity.api;

import android.os.AsyncTask;
import android.util.Log;

import com.example.heyrobin.mainactivity.domain.Photo;
import com.example.heyrobin.mainactivity.interfaces.OnPhotoAvailable;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by HeyRobin on 13-3-2018.
 */

public class PhotoTask extends AsyncTask<String, Void, String> {

    //Callback variable
    private OnPhotoAvailable listener = null;
    private final static String TAG = "PhotoTask";


    //Constructor: The requesting class is telling this class that it wants response
    public PhotoTask(OnPhotoAvailable listener) {
        this.listener = listener;
        Log.d(TAG, "Constructor PhotoTask called");
    }


    //Establish a internet connection
    @Override
    protected String doInBackground(String... strings) {

        //inputStream
        InputStream inputStream = null;

        //Getting the responsCode from the conncection (404 - NOT FOUND for example)
        int responseCode = -1;

        //URL retrieved from .execute()
        String photoUrl = strings[0];
        Log.d(TAG, "PhotoURL = " + photoUrl);

        //Result for return
        String response = "";


        //Setting up a connection
        try {

            //Create a new URL and opening the connection
            URL url = new URL(photoUrl);
            URLConnection urlConnection = url.openConnection();

            //Checking if the connection is not null
            if (!(urlConnection instanceof HttpURLConnection))  {
                return null;
            }

            //Establishing a HTTP connection
            HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;
            httpURLConnection.setAllowUserInteraction(false);
            httpURLConnection.setInstanceFollowRedirects(true);
            httpURLConnection.setRequestMethod("GET");

            //Execute the request
            httpURLConnection.connect();

            //Check if the request succeeded
            responseCode = httpURLConnection.getResponseCode();
            Log.d(TAG, "responseCode = " + responseCode);

            if (responseCode == httpURLConnection.HTTP_OK)  {
                inputStream = httpURLConnection.getInputStream();
                response = getStringFromInputStream(inputStream);
                Log.d(TAG, "Response = " + response);

            } else  {
                Log.d(TAG, "ERROR, Invalid Response");
            }

        } catch(Exception e)    {
            e.printStackTrace();
            return null;
        }

        return response;
    }


    //OnPostExecute processes the result from the doOnBackground method
    @Override
    protected void onPostExecute(String response) {
        Log.d(TAG, "OnPostExecute called. response = " + response);

        //Check if the response is not null
        if (response == null || response.equals("")) {
            Log.d(TAG, "OnPostExecute received an empty response");
            return;
        }

        //The response is a JSON object
        JSONObject jsonObject;

        try {

            //Creating a new JSON object with the response
            jsonObject = new JSONObject(response);

            //Retrieving all users from the object "results"
            JSONArray photos = jsonObject.getJSONArray("photos");

            //Start a loop to get all the data, with a max of 25 photo's
            for (int i = 0; i < photos.length(); i++)    {

                //Make a new user object from the user at position i
                JSONObject photo = photos.getJSONObject(i);
                int id = photo.getInt("id");
                String photoUrl = photo.getString("img_src");

                JSONObject camera = photo.getJSONObject("camera");
                String cameraName = camera.getString("full_name");
                Log.d(TAG, "Got the following results: id = " + id + ", photoUrl = " + photoUrl + ", cameraName: " + cameraName);

                Photo newPhoto = new Photo(id, photoUrl, cameraName);

                //create a callback with the new data
                listener.OnPhotoAvailable(newPhoto);
            }

        } catch(Exception e)    {
            e.printStackTrace();
        }
    }


    //Converting a inputStream to a String
    private static String getStringFromInputStream(InputStream is) {

        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        String line;
        try {

            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return sb.toString();
    }
}
