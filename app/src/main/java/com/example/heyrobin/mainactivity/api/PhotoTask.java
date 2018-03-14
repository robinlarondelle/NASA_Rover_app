package com.example.heyrobin.mainactivity.api;

import android.os.AsyncTask;
import android.util.Log;

import com.example.heyrobin.mainactivity.domain.Photo;
import com.example.heyrobin.mainactivity.adapters.OnPhotoAvailable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

//PhotoTask class made to retrieve the photo's from the NASA Api
public class PhotoTask extends AsyncTask<String, Void, String> {

    // Callback
    private OnPhotoAvailable listener = null;

    // Statics
    private static final String TAG = PhotoTask.class.getSimpleName();

    // Constructor, set listener
    public PhotoTask(OnPhotoAvailable listener) {
        this.listener = listener;
    }

    @Override
    protected String doInBackground(String... params) {

        InputStream inputStream = null;
        int responsCode = -1;
        // The URL to use .execute() on
        String photoUrl = params[0];
        // The result for return
        String response = "";

        Log.i(TAG, "doInBackground called. PhotoURL: " + photoUrl);

        //Setup a connection
        try {
            //URL connection made
            URL url = new URL(photoUrl);
            URLConnection urlConnection = url.openConnection();
            Log.d(TAG, "URL connection made");

            if (!(urlConnection instanceof HttpURLConnection)) {
                return null;
            }

            // Initiate a HTTP connection
            HttpURLConnection httpConnection = (HttpURLConnection) urlConnection;
            httpConnection.setAllowUserInteraction(false);
            httpConnection.setInstanceFollowRedirects(true);
            httpConnection.setRequestMethod("GET");

            // Execute the requesy
            httpConnection.connect();

            // retrieve the response code (404 - NOT FOUND for example)
            responsCode = httpConnection.getResponseCode();
            if (responsCode == HttpURLConnection.HTTP_OK) {
                inputStream = httpConnection.getInputStream();
                response = getStringFromInputStream(inputStream);
                Log.i(TAG, "doInBackground response = " + response);
            } else {
                Log.e(TAG, "Error, invalid response");
            }
        } catch (MalformedURLException e) {
            Log.e(TAG, "doInBackground MalformedURLEx " + e.getLocalizedMessage());
            return null;
        } catch (IOException e) {
            Log.e("TAG", "doInBackground IOException " + e.getLocalizedMessage());
            return null;
        }
        return response;
    }


    //Process the data from doOnBackground
    protected void onPostExecute(String response) {
        Log.i(TAG, "onPostExecute " + response);

        // Check for response
        if(response == null || response.equals("")) {
            Log.e(TAG, "onPostExecute kreeg een lege response!");
            return;
        }

        //Create a new JSON object
        JSONObject jsonObject;
        try {
            // Top level json object
            jsonObject = new JSONObject(response);

            // Get all users and start looping
            JSONArray photosArray = jsonObject.getJSONArray("photos");
            for(int idx = 0; idx < photosArray.length(); idx++) {
                // array level objects and get photos
                JSONObject photoObject = photosArray.getJSONObject(idx);

                // Get id, camera, en verkrijgt rover array ( name, max_date, total_photos
                String id = photoObject.getString("id");
                String cameraName =  photoObject.getJSONObject("camera").getString("full_name");
                String robotName = photoObject.getJSONObject("rover").getString("name");
                String imageDate = photoObject.getJSONObject("rover").getString("max_date");
                String imageTotal = photoObject.getJSONObject("rover").getString("total_photos");

                Log.i(TAG, "Got photo " + id + " " + cameraName);

                // Get image url
                String imageURL = photoObject.getString("img_src");

                // Create new Product object
                // Builder Design Pattern
                Photo photo = new Photo.PhotoBuilder(id, cameraName, robotName, imageDate, imageTotal, imageURL)
                        .setID(id)
                        .setCameraName(cameraName)
                        .setRobotName(robotName)
                        .setImageURL(imageURL)
                        .setImageInformation(imageDate, imageTotal)
                        .build();

                //Callback
                listener.OnPhotoAvailable(photo);
            }
        } catch( JSONException ex) {
            Log.e(TAG, "onPostExecute JSONException " + ex.getLocalizedMessage());
        }
    }

    // convert InputStream to String
    private static String getStringFromInputStream(InputStream is) {
        Log.d(TAG, "getStringFromInputStream aangeroepen.");

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
