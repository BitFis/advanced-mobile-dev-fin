package com.example.ex2.service;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
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

    private Response.Listener<String> processResponse = response -> {
        OpenWeatherResult result = gson.fromJson(response.getBytes().toString(), OpenWeatherResult.class);
    };

    public void getLatLng(LatLng latLng, Response.Listener<OpenWeatherResult> success, Response.ErrorListener errorListener) {
        OpenWeatherUrlSchema openWeatherUrlSchema = new OpenWeatherUrlSchema()
                .appid(APPID)
                .latLng(latLng);

        this.requestQueue.add(new StringRequest(
                Request.Method.GET,
                openWeatherUrlSchema.toString(),
                response -> {
                    try {
                        OpenWeatherService.this.processResponse.onResponse(response);
                    } catch (RuntimeException e) {
                        errorListener.onErrorResponse(new VolleyError("Parsing error occured", e));
                    }
                },
                errorListener
        ));
    }

}
