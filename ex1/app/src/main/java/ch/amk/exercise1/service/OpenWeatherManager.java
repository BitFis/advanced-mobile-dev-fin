package ch.amk.exercise1.service;

import android.util.Log;

import com.android.volley.RequestQueue;
import com.google.gson.Gson;

import javax.inject.Inject;

public class OpenWeatherManager {
    private final static String TAG = "OpenWeatherManager";

    protected Gson gson;

    protected RequestQueue requestQueue;

    @Inject public OpenWeatherManager(
            Gson gson,
            RequestQueue requestQueue
    ) {
        this.gson = gson;
        this.requestQueue = requestQueue;
        Log.i(TAG, "Hello World");
    }

}
