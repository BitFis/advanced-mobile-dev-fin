package com.example.ex2.service;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.ex2.models.openweather.OpenWeatherForcast;
import com.example.ex2.models.openweather.OpenWeatherResult;
import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;

import org.apache.commons.lang3.NotImplementedException;

import javax.inject.Inject;

public class OpenWeatherService {

    private static final String APPID = "01f4ce4de22786346799bfec88c18db8";

    private RequestQueue requestQueue;
    private Gson gson;

    @Inject
    public OpenWeatherService(RequestQueue requestQueue, Gson gson) {
        this.gson = gson;
        this.requestQueue = requestQueue;
    }

    private void wrapRequest(String url, Response.Listener<String> success, Response.ErrorListener errorListener) {
        this.requestQueue.add(new StringRequest(
                Request.Method.GET,
                url,
                response -> {
                    try {
                        success.onResponse(response);
                    } catch (RuntimeException ex) {
                        Log.e("OpenWeatherService", "processing request, exception occured", ex);
                        errorListener.onErrorResponse(new VolleyError(ex));
                    }
                },
                error -> {
                    Log.e("OpenWeatherService", "during request '" + url + "', error occured", error);
                    errorListener.onErrorResponse(error);
                }
        ));
    }

    public void getLatLng(LatLng latLng, Response.Listener<OpenWeatherResult> success, Response.ErrorListener errorListener) {
        OpenWeatherUrlSchema openWeatherUrlSchema = new OpenWeatherUrlSchema()
                .appid(APPID)
                .latLng(latLng);

        this.wrapRequest(
                openWeatherUrlSchema.toString(),
                response -> success.onResponse(gson.fromJson(response, OpenWeatherResult.class)),
                errorListener
        );
    }

    public void getForcastLatLng(LatLng latLng, Response.Listener<OpenWeatherForcast> success, Response.ErrorListener errorListener) {
        OpenWeatherUrlSchema openWeatherUrlSchema = new OpenWeatherUrlSchema()
                .appid(APPID)
                .latLng(latLng)
                .endpoint(OpenWeatherUrlSchema.ENDPOINT.FORCAST5);

        this.wrapRequest(
                openWeatherUrlSchema.toString(),
                response -> success.onResponse(gson.fromJson(response, OpenWeatherForcast.class)),
                errorListener
        );
    }

}
