package com.example.ex2.service;

import com.android.volley.RequestQueue;
import com.google.android.gms.maps.model.LatLng;

import javax.inject.Inject;

public class OpenWeatherService {

    private RequestQueue requestQueue;

    @Inject
    public OpenWeatherService(RequestQueue requestQueue) {
        this.requestQueue = requestQueue;
    }

    public String get(LatLng latLng) {
        return "Original";
    }

}
