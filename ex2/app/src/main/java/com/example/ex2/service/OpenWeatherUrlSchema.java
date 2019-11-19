package com.example.ex2.service;

import android.net.Uri;

import androidx.annotation.NonNull;

import com.google.android.gms.maps.model.LatLng;
import com.google.common.collect.Maps;

import java.util.HashMap;

public class OpenWeatherUrlSchema {

    private static String OPENWEATHER_API_AUTHORITY = "api.openweathermap.org";
    private static String OPENWEATHER_API_PATH = "/data/2.5/";

    private static String DEFAULT_UNIT = "metric";
    private static String DEFAULT_APPID = "xxxxxxxxxxxxxxxxxxxxxx";

    private static String KEY_UNITS = "units";
    private static String KEY_APPID = "appid";
    private static String KEY_LATITUDE = "lat";
    private static String KEY_LONGITUDE = "lon";

    private String appid = DEFAULT_APPID;
    private HashMap<String, String> parameters = new HashMap<>();
    private ENDPOINT endpoint = ENDPOINT.WEATHER;

    public enum ENDPOINT {
        WEATHER("weather"),
        FORCAST5("forecast");

        private String endpoint;

        ENDPOINT(String endpoint) {
            this.endpoint = endpoint;
        }

        public String get() {
            return this.endpoint;
        }
    }

    public OpenWeatherUrlSchema() {
    }

    public OpenWeatherUrlSchema appid(String appid) {
        this.appid = appid;
        return this;
    }

    public OpenWeatherUrlSchema latLng(LatLng latLng) {
        this.parameters.put(KEY_LONGITUDE, String.valueOf(latLng.longitude));
        this.parameters.put(KEY_LATITUDE, String.valueOf(latLng.latitude));
        return this;
    }

    public OpenWeatherUrlSchema endpoint(ENDPOINT endpoint) {
        this.endpoint = endpoint;
        return this;
    }

    @NonNull
    @Override
    public String toString() {
        Uri.Builder builder = new Uri.Builder()
                .scheme("https")
                .authority(OPENWEATHER_API_AUTHORITY)
                .path(OPENWEATHER_API_PATH)
                .appendPath(this.endpoint.get())
                .appendQueryParameter(KEY_UNITS, DEFAULT_UNIT)
                .appendQueryParameter(KEY_APPID, this.appid);

        this.parameters.forEach((s, s2) -> builder.appendQueryParameter(s, s2));

        return builder.toString();
    }
}
