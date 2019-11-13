package ch.amk.exercise1.service;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;

import javax.inject.Inject;

import ch.amk.exercise1.models.openweather.OpenWeather;

public class OpenWeatherManager {
    private final static String TAG = "OpenWeatherManager";

    private final static String OPENWEATHER_URL = "https://api.openweathermap.org/data/2.5/weather";
    private final static String OPENWEATHER_APPID = "01f4ce4de22786346799bfec88c18db8";
    private final static String OPENWEATHER_UNIT = "metric";

    protected Gson gson;

    protected RequestQueue requestQueue;

    @Inject public OpenWeatherManager(
            Gson gson,
            RequestQueue requestQueue
    ) {
        this.gson = gson;
        this.requestQueue = requestQueue;
    }

    private Response.Listener<String> createOnLoaded(Response.Listener<OpenWeather> listener) {
        return response -> {
            listener.onResponse(this.gson.fromJson(response, OpenWeather.class));
        };
    }

    private String createUrl(String city) {
        return new StringBuilder()
            .append(OpenWeatherManager.OPENWEATHER_URL)
            .append("?q=")
            .append(city)
            .append("&units=")
            .append(OpenWeatherManager.OPENWEATHER_UNIT)
            .append("&appid=")
            .append(OpenWeatherManager.OPENWEATHER_APPID)
            .toString();
    }

    public void get(String city, Response.Listener<OpenWeather> success, Response.ErrorListener errorListener) {
        StringRequest request = new StringRequest(
                Request.Method.GET,
                this.createUrl(city),
                this.createOnLoaded(success),
                error -> errorListener.onErrorResponse(error)
        );

        this.requestQueue.add(request);
    }

}
