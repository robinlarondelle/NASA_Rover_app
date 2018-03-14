package com.example.heyrobin.mainactivity.api;

/**
 * Created by HeyRobin on 13-3-2018.
 */

public class Api {

    public static String getApiNASA()   {
        return "https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?sol=1000&camera=mast&api_key="+Keys.getNASAApiKey();
    }
}
